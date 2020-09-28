package com.fastenum.fenum.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description : redis的服务类
 * @Author : zhangMing
 * @Date : Created in 14:38 2020-09-28
 */
@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool;

    /**
     * 获取单个实体 String类型
     * @param redisKeyConfig
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getOfString(RedisKeyConfig redisKeyConfig, String key,  Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            //生成真正的key
            String realKey = redisKeyConfig.getPrefix() + key;
            String value = jedis.get(realKey);
            T t =  stringToBean(value, clazz);
            return t;
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 获取单个实体 map类型
     * @param redisKeyConfig
     * @param key
     * @param minKey
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getOfMap(RedisKeyConfig redisKeyConfig, String key , String minKey,  Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            //生成真正的key
            String realKey = redisKeyConfig.getPrefix() + key;
            String value = jedis.hget(realKey , minKey);
            T t =  stringToBean(value, clazz);
            return t;
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 获取map中全部的值
     * @param redisKeyConfig
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> Map<String, T> getAllOfMap(RedisKeyConfig redisKeyConfig, String key ,  Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            //生成真正的key
            String realKey = redisKeyConfig.getPrefix() + key;
            Map<String, String> valueMap = jedis.hgetAll(realKey);
            Map<String, T> resultMap = new HashMap<>(  );
            for (String str : valueMap.keySet()){
                T t =  stringToBean(valueMap.get( str ), clazz);
                resultMap.put( str , t );
            }
            return resultMap;
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 设置string类型缓存
     * @param redisKeyConfig
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean setOfString(RedisKeyConfig redisKeyConfig, String key,  T value) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            String str = beanToString(value);
            if(str == null || str.length() <= 0) {
                return false;
            }
            //生成真正的key
            String realKey  = redisKeyConfig.getPrefix() + key;
            int seconds =  redisKeyConfig.getExpireSeconds();
            if(seconds <= 0) {
                jedis.set(realKey, str);
            }else {
                jedis.setex(realKey, seconds, str);
            }
            return true;
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 设置map类型缓存
     * @param redisKeyConfig
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean setOfMap(RedisKeyConfig redisKeyConfig, String key, String minKey, T value) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            String str = beanToString(value);
            if(str == null || str.length() <= 0) {
                return false;
            }
            //生成真正的key
            String realKey  = redisKeyConfig.getPrefix() + key;
            int seconds =  redisKeyConfig.getExpireSeconds();
            //目前redis不支持hsetex。注意这里，如果异常退出会一直不过期。
            jedis.hset(realKey, minKey, str);
            if(seconds > 0) {
                jedis.expire( realKey , seconds );
            }
            return true;
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * bean转换为string
     * @param value
     * @param <T>
     * @return
     */
    public static <T> String beanToString(T value) {
        if(value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if(clazz == int.class || clazz == Integer.class) {
            return value.toString();
        }else if(clazz == String.class) {
            return (String)value;
        }else if(clazz == long.class || clazz == Long.class) {
            return value.toString();
        }else {
            return JSON.toJSONString( value );
        }
    }

    /**
     * string转换成实体
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T stringToBean(String str, Class<T> clazz) {
        if(str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if(clazz == int.class || clazz == Integer.class) {
            return (T)Integer.valueOf(str);
        }else if(clazz == String.class) {
            return (T)str;
        }else if(clazz == long.class || clazz == Long.class) {
            return  (T)Long.valueOf(str);
        }else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }


    /**
     * redis线程异常则关闭该线程
     * @param jedis
     */
    private void returnToPool(Jedis jedis) {
        if(jedis != null) {
            jedis.close();
        }
    }

}
