package com.push4j.cache;

import org.fastboot.common.utils.ToolsKit;
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
		// 根据code缓存
		CacheKeyModel cacheKeyModel = new CacheKeyModel.Builder(TemplateCacheKeyEnum.TEMPLATE_CODE).customKey(entity.getCode()).build();
		RedisFactory.getCache().set(cacheKeyModel, entity);

		cacheKeyModel = new CacheKeyModel.Builder(TemplateCacheKeyEnum.HSET_KEY).build();
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
		TemplateEntity templateEntity = findById(id);
		CacheKeyModel cacheKeyModel = null;
		// 先删除by code的缓存
		if (ToolsKit.isNotEmpty(templateEntity)) {
			cacheKeyModel = new CacheKeyModel.Builder(TemplateCacheKeyEnum.TEMPLATE_CODE).customKey(templateEntity.getCode()).build();
			RedisFactory.getCache().del(cacheKeyModel);
		}

		cacheKeyModel = new CacheKeyModel.Builder(TemplateCacheKeyEnum.HSET_KEY).build();
		return RedisFactory.getCache().hdel(cacheKeyModel, id).intValue();
	}

	/**
	 * 根据id查找缓存记录
	 *
	 * @param entity
	 * @return 存在返回记录对象
	 */
	public TemplateEntity findByCode(String code) {
		CacheKeyModel cacheKeyModel = new CacheKeyModel.Builder(TemplateCacheKeyEnum.TEMPLATE_CODE).customKey(code).build();
		return RedisFactory.getCache().get(cacheKeyModel);
	}

}
