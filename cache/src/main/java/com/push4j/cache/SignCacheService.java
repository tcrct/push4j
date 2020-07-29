package com.push4j.cache;

import com.push4j.cache.enums.SignCacheKeyEnum;
import com.push4j.entity.SignEntity;
import org.fastboot.common.utils.ToolsKit;
import org.fastboot.redis.RedisFactory;
import org.fastboot.redis.core.CacheKeyModel;
import org.fastboot.redis.crud.ICurdCacheService;
import org.springframework.stereotype.Service;

/**
 * 签名缓存服务
 *
 * @author Laotang
 * @since 1.0
 */
@Service
public class SignCacheService implements ICurdCacheService<SignEntity> {

    /**
     * 保存记录到缓存
     *
     * @param entity
     * @return 保存成功返回true
     */
    @Override
    public Integer save(SignEntity entity) {

        CacheKeyModel cacheKeyModel = new CacheKeyModel.Builder(SignCacheKeyEnum.SIGN_KEY).customKey(entity.getKey()).build();
        RedisFactory.getCache().set(cacheKeyModel, entity);

        cacheKeyModel = new CacheKeyModel.Builder(SignCacheKeyEnum.SIGN_KEY).build();
        return RedisFactory.getCache().hset(cacheKeyModel, entity.getId(), entity).intValue();
    }

    @Override
    public SignEntity findById(String key) {
        CacheKeyModel cacheKeyModel = new CacheKeyModel.Builder(SignCacheKeyEnum.SIGN_KEY).build();
        return RedisFactory.getCache().hget(cacheKeyModel,key);
    }

    @Override
    public Integer deleteById(String key) {
        SignEntity signEntity = findById(key);
        if (ToolsKit.isNotEmpty(signEntity)) {
            CacheKeyModel cacheKeyModel = new CacheKeyModel.Builder(SignCacheKeyEnum.SIGN_KEY).customKey(signEntity.getKey()).build();
            RedisFactory.getCache().del(cacheKeyModel);
        }
        CacheKeyModel cacheKeyModel = new CacheKeyModel.Builder(SignCacheKeyEnum.SIGN_KEY).build();
        return RedisFactory.getCache().hdel(cacheKeyModel, key).intValue();
    }

    public SignEntity findByKey(String key) {
        CacheKeyModel cacheKeyModel = new CacheKeyModel.Builder(SignCacheKeyEnum.SIGN_KEY).customKey(key).build();
        return RedisFactory.getCache().get(cacheKeyModel);
    }
}
