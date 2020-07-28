package com.push4j.cache.enums;


import org.fastboot.redis.core.ICacheKeyEnums;

/**
 * 签名缓存KEY枚举
 *
 * @author Laotang
 * @since 1.0
 */
public enum SignCacheKeyEnum implements ICacheKeyEnums {

    /**
     * key前缀，过期时间，说明
     */
    SIGN_KEY("mpay:push:sign:", ICacheKeyEnums.ONE_MONTH_TTL, "App签名缓存Key");


    private String keyPrefix;
    private int  ttl;
    private String keyDesc;
    private SignCacheKeyEnum(String keyPrefix, int ttl, String keyDesc) {
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
