package com.fastenum.fenum.redis;

/**
 * @Description : 代码枚举值的redis配置
 * @Author : zhangMing
 * @Date : Created in 14:49 2020-09-28
 */
public class EnumConfig extends BaseRedisKeyConfig {


    private EnumConfig(String prefix , int expireSeconds) {
        super(prefix, expireSeconds);
    }

    public static EnumConfig fcode = new EnumConfig( "fcode" , 1000);

}
