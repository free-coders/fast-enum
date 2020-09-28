package com.fastenum.fenum.redis;

/**
 * @Description : redis键配置
 * @Author : zhangMing
 * @Date : Created in 14:42 2020-09-28
 */
public interface RedisKeyConfig {

    /**
     * 获取过期时间
     * @return
     */
    public int getExpireSeconds();

    /**
     * 获取前缀
     * @return
     */
    public String getPrefix();
}
