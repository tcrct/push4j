package com.push4j.utils;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.push4j.dto.PushDataDto;
import com.push4j.utils.template.AbstractPushTemplate;
import org.fastboot.common.utils.LogUtils;
import org.fastboot.common.utils.SettingKit;
import org.fastboot.common.utils.SpringKit;
import org.fastboot.exception.common.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

/**
 * Created by laotang on 2020/7/30.
 */
public class PushKit {

    private static final Logger LOGGER = LoggerFactory.getLogger(PushKit.class);

    private static class PushKitHolder {
        private static final PushKit INSTANCE = new PushKit();
    }
    private PushKit() {
        if (null== PUSH_URL) {
            PUSH_URL = SettingKit.duang().key("push.url").getString();
            if (ToolsKit.isEmpty(PUSH_URL)) {
                throw new ServiceException(123, "没有指定推送中心的URL地址，请在application.properties里设置[push.url]值。");
            }
            String templateClass = SettingKit.duang().key("push.data.template").getString();
            if (ToolsKit.isEmpty(templateClass)) {
                throw new ServiceException(123, "没有指定发送的数据模板类，请在application.properties里设置[push.data.template]值。");
            }
            PUSH_TEMPLATE = ReflectUtil.newInstance(templateClass);
        }
    }
    public static final PushKit duang() {
        dataDto= new PushDataDto();
        return PushKitHolder.INSTANCE;
    }

    private static String PUSH_URL;
    private AbstractPushTemplate PUSH_TEMPLATE;
    private static PushDataDto dataDto;

    public PushKit account(String account) {
        dataDto.setAccount(account);
        return this;
    }
    public PushKit title(String title) {
        dataDto.setTitle(title);
        return this;
    }
    public PushKit content(String content) {
        dataDto.setContent( content);
        return this;
    }
    public PushKit phoneSystem(String phoneSystem) {
        dataDto.setPhoneSystem(phoneSystem);
        return this;
    }

    /**
     * 发送，以http的方式请求推送平台
     */
    public synchronized PushDataDto push() {
        String dataString = PUSH_TEMPLATE.pushData(dataDto);
        dataDto.setReplaceContent(dataString);
        LogUtils.log(LOGGER, "推出的内容： "+ dataString);
        try {
            HttpResponse httpResponse = HttpRequest.post(PUSH_URL)
                    .body(dataString, MediaType.APPLICATION_JSON.toString())
                    .execute();
            if (httpResponse.isOk()) {
                String responseBody = httpResponse.body();
                System.out.println("responseBody: " + responseBody);
            }
        } catch (Exception e) {
            LogUtils.log(LOGGER, e.getMessage(), e);
        }
        return dataDto;
    }

    /**
     * 查询状态
     * @return
     */
    public String status() {
        return null;
    }
}
