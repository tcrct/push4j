package com.push4j.cache;

import org.fastboot.redis.RedisFactory;
import org.fastboot.redis.core.CacheKeyModel;
import org.fastboot.redis.crud.ICurdCacheService;
import org.springframework.stereotype.Service;
import com.push4j.cache.enums.TemplateCacheKeyEnum;
import com.push4j.entity.TemplateEntity;

/**
* 
*
* @author zat
* @since 1.0
*/
@Service
public class TemplateCacheService implements ICurdCacheService<TemplateEntity> {
	/**
	* 保存记录到缓存
	*
	* @param entity
	* @return 保存成功返回true
	*/
	@Override
	public Integer save(TemplateEntity entity) {
		CacheKeyModel cacheKeyModel = new CacheKeyModel.Builder(TemplateCacheKeyEnum.HSET_KEY).build();
		return RedisFactory.getCache().hset(cacheKeyModel, entity.getId(), entity).intValue();
	}

	/**
	* 根据id查找缓存记录
	*
	* @param entity
	* @return 存在返回记录对象
	*/
	@Override
	public TemplateEntity findById(String id) {
		CacheKeyModel cacheKeyModel = new CacheKeyModel.Builder(TemplateCacheKeyEnum.HSET_KEY).build();
		return RedisFactory.getCache().hget(cacheKeyModel, id);
	}

	/**
	* 根据id删除缓存记录
	*
	* @param id 记录ID
	* @return 删除成功返回记录个数
	*/
	@Override
	public Integer deleteById(String id) {
		CacheKeyModel cacheKeyModel = new CacheKeyModel.Builder(TemplateCacheKeyEnum.HSET_KEY).build();
		return RedisFactory.getCache().hdel(cacheKeyModel, id).intValue();
	}

}
