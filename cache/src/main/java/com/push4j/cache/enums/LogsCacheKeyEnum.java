package com.push4j.cache.enums;

import org.fastboot.redis.core.ICacheKeyEnums;

/**
* Logs缓存KEY枚举
*
* @author zat
* @since 1.0
*/
public enum LogsCacheKeyEnum implements ICacheKeyEnums {

	HSET_KEY("mpay:模块名:实体名:", ICacheKeyEnums.ONE_DAY_TTL, "记录缓存到hset的key"),

	;
	private String keyPrefix;
	private int  ttl;
	private String keyDesc;
	/**
	*@param keyPrefix 缓存关键字前缀
	*@param ttl 缓存有效时间
	*@param keyDesc 缓存关键字说明
	*/
	private LogsCacheKeyEnum(String keyPrefix, int ttl, String keyDesc) {
		this.keyPrefix = keyPrefix;
		this.ttl = ttl;
		this.keyDesc = keyDesc;
	}
	public String getKeyPrefix() {
		return keyPrefix;
	}
	public int getKeyTTL() {
		return ttl;
	}
	public String getKeyDesc() {
		return keyDesc;
	}
}
