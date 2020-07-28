package com.push4j.service;

import com.push4j.entity.SignEntity;
import org.fastboot.common.utils.ToolsKit;
import org.fastboot.db.curd.CurdService;
import org.springframework.stereotype.Service;

/**
 * 签名服务类
 *
 * @author Laotang
 * @since 1.0
 */
@Service
public class SignService extends CurdService<SignEntity> {

    /**
     * 保存操作
     * @param entity 待持久化/更新的对象
     * @return
     */
    public Integer save(SignEntity entity) {
        if (ToolsKit.isEmpty(entity.getId())) {
            entity = new SignEntity(entity.getName(), entity.getDesc());
        } else {
            // 更新时不更新key与secret值
            entity.setKey(null);
            entity.setSecret(null);
        }
        return super.save(entity);
    }

}
