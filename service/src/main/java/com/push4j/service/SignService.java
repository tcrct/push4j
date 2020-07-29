package com.push4j.service;

import com.push4j.cache.SignCacheService;
import com.push4j.dao.SignEntityDao;
import com.push4j.entity.SignEntity;
import org.beetl.sql.core.SQLReady;
import org.fastboot.common.utils.ToolsKit;
import org.fastboot.db.curd.CurdService;
import org.fastboot.exception.common.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 签名服务类
 *
 * @author Laotang
 * @since 1.0
 */
@Service
public class SignService extends CurdService<SignEntity> {

    @Autowired
    private SignCacheService signCacheService;

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


    /**
     *
     * @param accessKey
     * @return
     */
    public SignEntity findByKey(String accessKey) {
        if (ToolsKit.isEmpty(accessKey)) {
            throw new ServiceException(201, "accessKey不能为空");
        }
        SignEntity entity = signCacheService.findByKey(accessKey);
        if (ToolsKit.isEmpty(entity)) {
            String sql = "select * from sign where `key`=?";
            List<SignEntity> signEntityList = manager.execute(new SQLReady(sql, accessKey), SignEntity.class);
            return ToolsKit.isEmpty(signEntityList) ? null : signEntityList.get(0);
        }
        return null;
    }
}
