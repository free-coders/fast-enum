package com.fastenum.fenum.redis;

/**
 * @Description : 代码枚举值的redis配置
 * @Author : zhangMing
 * @Date : Created in 14:49 2020-09-28
 */
public class CodeEnumConfig extends BaseRedisKeyConfig {


    private CodeEnumConfig( String prefix , int expireSeconds) {
        super(prefix, expireSeconds);
    }

    public static CodeEnumConfig fcode = new CodeEnumConfig( "fcode" , 1000);

}
