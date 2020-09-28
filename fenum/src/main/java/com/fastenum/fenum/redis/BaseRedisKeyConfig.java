package com.fastenum.fenum.redis;

import lombok.Data;

/**
 * @Description : redis配置的基本类
 * @Author : zhangMing
 * @Date : Created in 14:46 2020-09-28
 */
@Data
public abstract class BaseRedisKeyConfig implements RedisKeyConfig {

    /**
     * 前缀
     */
    private String prefix;

    /**
     * 过期时间
     */
    private int expireSeconds;

    public BaseRedisKeyConfig(String prefix , int expireSeconds){
        this.prefix = prefix;
        this.expireSeconds = expireSeconds;
    }



}
