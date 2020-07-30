package com.push4j.cache.enums;

import org.fastboot.redis.core.ICacheKeyEnums;

/**
* Template缓存KEY枚举
*
* @author zat
* @since 1.0
*/
public enum TemplateCacheKeyEnum implements ICacheKeyEnums {

	HSET_KEY("mpay:push:template:", ICacheKeyEnums.ONE_MONTH_TTL, "记录缓存到hset的key"),
	TEMPLATE_CODE("mpay:push:template:", ICacheKeyEnums.ONE_MONTH_TTL, "根据code将模板信息缓存"),
	;
	private String keyPrefix;
	private int  ttl;
	private String keyDesc;
	/**
	*@param keyPrefix 缓存关键字前缀
	*@param ttl 缓存有效时间
	*@param keyDesc 缓存关键字说明
	*/
	private TemplateCacheKeyEnum(String keyPrefix, int ttl, String keyDesc) {
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
