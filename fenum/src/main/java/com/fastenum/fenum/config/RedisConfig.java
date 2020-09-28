package com.fastenum.fenum.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description : redis的配置信息
 * @Author : zhangMing
 * @Date : Created in 14:30 2020-09-28
 */
@Data
@Component
@ConfigurationProperties(prefix="redis")
public class RedisConfig {

    /**
     * 地址
     */
    private String host;

    /**
     * 端口
     */
    private int port;

    /**
     * 超时
     */
    private int timeout;

    /**
     * 密码
     */
    private String password;

    /**
     * 最大线程数
     */
    private int poolMaxTotal;

    /**
     * 最大等待连接中的数量
     */
    private int poolMaxIdle;

    /**
     * 最大等待毫秒数
     */
    private int poolMaxWait;

}
