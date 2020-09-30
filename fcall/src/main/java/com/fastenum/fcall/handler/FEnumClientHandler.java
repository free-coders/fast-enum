package com.fastenum.fcall.handler;

import com.fastenum.annotation.FEnumClient;
import com.fastenum.annotation.FEnumMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description : 通过代码把接口生成类
 * @Author : zhangMing
 * @Date : Created in 10:44 2020-09-30
 */
@Slf4j
public class FEnumClientHandler<T> implements InvocationHandler {

    Map<String , Field[]> fieldMap = new ConcurrentHashMap<>(  );

    private Class<T> interfaceType;

    private RestTemplate restTemplate;

    private final String PROTOCOL_HTTP = "http://";

    /**
     * 通过构造方法获取springbean
     * @param interfaceType
     * @param restTemplate
     */
    public FEnumClientHandler(Class<T> interfaceType , RestTemplate restTemplate) {
        this.interfaceType = interfaceType;
        this.restTemplate = restTemplate;
    }


    /**
     * 代理类
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //设置完整路径
        StringBuffer URLBuffer = new StringBuffer( PROTOCOL_HTTP );
        FEnumClient fEnumClient = interfaceType.getAnnotation( FEnumClient.class );
        String projectPath = fEnumClient.value();
        URLBuffer.append( projectPath );
        FEnumMethod fEnumMethod = method.getAnnotation( FEnumMethod.class );
        String methodPath = fEnumMethod.value();
        URLBuffer.append( methodPath ).append( "?" );
        //获取返回类型
        Class returnType = method.getReturnType();
        //获取传入的参数
        Map<String, Object> paramMap = new HashMap<>(  );
        for (Object obj : args){
            paramMap.put( "enumCondition" , obj);
            //由于反射增加性能开销，此处增加缓存
            String name = obj.getClass().getName();
            Field[] fields = fieldMap.get( name );
            if (null == fields){
                fields = obj.getClass().getDeclaredFields();
                fieldMap.put( name , fields );
            }
            for (Field field : fields){
                field.setAccessible(true);
                //todo 后续需要增加多种类型
                Object o = field.get( obj );
                if(null != o){
                    URLBuffer.append( field.getName() ).append( "=" ).append( o );
                }
            }
        }
        //开始远程调用
        return restTemplate.getForObject(URLBuffer.toString() , returnType);
    }

}
