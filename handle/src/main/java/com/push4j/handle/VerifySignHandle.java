package com.push4j.handle;

import com.push4j.entity.SignEntity;
import com.push4j.service.SignService;
import org.fastboot.common.annotation.Handler;
import org.fastboot.common.handler.IHandler;
import org.fastboot.common.utils.SpringKit;
import org.fastboot.common.utils.ToolsKit;
import org.fastboot.exception.common.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证签名处理器
 * 所有请求头里必须带有accessKey与accessSecret，验证通过后放行
 *
 * @author Laotang
 * @since 1.0
 */
@Handler(sort = 0)
public class VerifySignHandle implements IHandler {

    private static final String ACCESS_KEY_FIELD = "x-push-accessKey";
    private static final String ACCESS_SECRET_FIELD = "x-push-accessSecret";
    private static final String REQUEST_ID = "x-push-request-id";

    @Override
    public boolean handler(HttpServletRequest request, HttpServletResponse response) {

        String uri = request.getRequestURI();
        // 只对推送接口进行验证
        if (!"/mpay/push".equals(uri)) {
            return true;
        }

        String requestId = request.getHeader(REQUEST_ID);
        String accessKey = request.getHeader(ACCESS_KEY_FIELD);
        String accessSecret = request.getHeader(ACCESS_SECRET_FIELD);

        if (ToolsKit.isEmpty(requestId) || ToolsKit.isEmpty(accessKey) || ToolsKit.isEmpty(accessSecret)) {
            throw new ServiceException(5501, "非法请求，请求头中没有包含必要的请求值");
        }

        SignEntity entity = SpringKit.getBean(SignService.class).findByKey(accessKey);
        if (ToolsKit.isEmpty(entity)) {
            throw new ServiceException(201, String.format("非法请求，根据[%s]找不到对应的实体对象", accessKey));
        }
        if (!accessKey.equals(entity.getKey()) || !accessSecret.equals(entity.getSecret())) {
            throw new ServiceException(201, String.format("非法请求，提交的key[%s]与secret[%s]与系统中的不匹配", accessKey,accessSecret));
        }
        // 返回true，进入下一个Handle
        return true;
    }
}
