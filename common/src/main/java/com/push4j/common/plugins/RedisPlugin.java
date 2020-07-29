package com.push4j.common.plugins;

import org.fastboot.common.annotation.Plugin;
import org.fastboot.common.plugins.IPlugin;
import org.fastboot.common.utils.LogUtils;
import org.fastboot.common.utils.SettingKit;
import org.fastboot.redis.RedisConfig;
import org.fastboot.redis.RedisFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Redis缓存插件，在程序启动完成后执行
 *
 * @author Laotang
 * @since 1.0
 */
@Plugin(sort = 0)
public class RedisPlugin implements IPlugin {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisPlugin.class);

    /**
     * 在springboot启动前执行
     */
    @Override
    public void before() {
    }

    /**
     * 在springboot启动后执行
     */
    @Override
    public void after() {
        RedisConfig redisConfig = new RedisConfig.Builder()
                .clientName(SettingKit.duang().key("redis.client.name").defaultValue("push4j").getString())
                .host(SettingKit.duang().key("redis.host").defaultValue("127.0.0.1").getString())
                .port(SettingKit.duang().key("redis.port").defaultValue(6379).getInteger())
                .password(SettingKit.duang().key("redis.password").defaultValue("123456").getString())
                .timeout(SettingKit.duang().key("redis.timeout").defaultValue(3000).getInteger())
                .database(SettingKit.duang().key("redis.database").defaultValue(0).getInteger())
                .build();
        RedisFactory factory = new RedisFactory(redisConfig);
       if (factory.start()) {
           LogUtils.log(LOGGER, "RedisPlugin[{}]-[{}:{}] start is success!",
                   redisConfig.getClientName(),
                   redisConfig.getHost(),
                   redisConfig.getPort());
       }
    }
}
