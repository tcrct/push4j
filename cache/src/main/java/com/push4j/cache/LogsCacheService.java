package com.push4j.cache;

import org.fastboot.common.utils.ToolsKit;
import org.fastboot.redis.RedisFactory;
import org.fastboot.redis.core.CacheKeyModel;
import org.fastboot.redis.crud.ICurdCacheService;
import org.springframework.stereotype.Service;
import com.push4j.cache.enums.LogsCacheKeyEnum;
import com.push4j.entity.LogsEntity;

import java.util.Map;
import java.util.Set;

/**
* Logs
*
* @author zat
* @since 1.0
*/
@Service
public class LogsCacheService implements ICurdCacheService<LogsEntity> {
	/**
	* 保存记录到缓存
	*
	* @param entity
	* @return 保存成功返回true
	*/
	@Override
	public Integer save(LogsEntity entity) {
		CacheKeyModel cacheKeyModel = new CacheKeyModel.Builder(LogsCacheKeyEnum.HSET_KEY).build();
		return RedisFactory.getCache().hset(cacheKeyModel, entity.getId(), entity).intValue();
	}

	/**
	* 根据id查找缓存记录
	*
	* @param id
	* @return 存在返回记录对象
	*/
	@Override
	public LogsEntity findById(String id) {
		CacheKeyModel cacheKeyModel = new CacheKeyModel.Builder(LogsCacheKeyEnum.HSET_KEY).build();
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
		CacheKeyModel cacheKeyModel = new CacheKeyModel.Builder(LogsCacheKeyEnum.HSET_KEY).build();
		return RedisFactory.getCache().hdel(cacheKeyModel, id).intValue();
	}

	/**
	 * 消息未读缓存，以messageId为key作集合，如果集合里存在userId的记录，则认为这条
	 * @param userId
	 * @param messageId
	 * @return
	 */
	public Long unread(String userId, String messageId) {
		CacheKeyModel cacheKeyModel = new CacheKeyModel.Builder(LogsCacheKeyEnum.UNREAD_KEY).customKey(userId).build();
		return RedisFactory.getCache().hset(cacheKeyModel, messageId, userId);
	}

	/***
	 * 根据用户ID与消息ID，将缓存中未读消息的记录删除，(已读)
	 * @param userId 用户ID
	 * @param messageId 消息ID
	 * @return
	 */
	public Long reader(String userId, String messageId) {
		CacheKeyModel cacheKeyModel = new CacheKeyModel.Builder(LogsCacheKeyEnum.UNREAD_KEY).customKey(userId).build();
		return RedisFactory.getCache().hdel(cacheKeyModel, messageId);
	}

	/**
	 * 取出该用户所有未读的消息
	 * @param userId 用户ID
	 * @return 未读消息的ID集合
	 */
	public Set<String> getAllUnReadMessage(String userId) {
		CacheKeyModel cacheKeyModel = new CacheKeyModel.Builder(LogsCacheKeyEnum.UNREAD_KEY).customKey(userId).build();
		Map<String,Object> unReadMap = RedisFactory.getCache().hgetAll(cacheKeyModel);
		return ToolsKit.isEmpty(unReadMap) ? null : unReadMap.keySet();
	}

}
