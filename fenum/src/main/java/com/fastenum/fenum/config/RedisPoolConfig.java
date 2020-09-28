package com.fastenum.fenum.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description : redisPool配置
 * @Author : zhangMing
 * @Date : Created in 14:23 2020-09-28
 */
@Configuration
public class RedisPoolConfig {

    @Autowired
    RedisConfig redisConfig;

    /**
     * 获取redis池
     * @return
     */
    @Bean
    public JedisPool JedisPoolFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle( redisConfig.getPoolMaxIdle() );
        poolConfig.setMaxTotal( redisConfig.getPoolMaxTotal() );
        poolConfig.setMaxWaitMillis( redisConfig.getPoolMaxWait() * 1000);
        JedisPool jp = new JedisPool(poolConfig, redisConfig.getHost(), redisConfig.getPort(),
                redisConfig.getTimeout() * 1000, redisConfig.getPassword() , 0);
        return jp;
    }

}
