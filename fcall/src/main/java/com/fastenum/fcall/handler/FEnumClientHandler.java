package com.fastenum.fcall.handler;

import com.alibaba.fastjson.JSONObject;
import com.fastenum.annotation.FEnumClient;
import com.fastenum.annotation.FEnumMethod;
import com.fastenum.condition.EnumCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.netflix.eureka.registry.Key.KeyType.JSON;

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

    private final String HTTP_POST = "POST";

    private final String HTTP_GET = "GET";

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
        URLBuffer.append( methodPath );
        Class returnType = method.getReturnType();
        //判断post\get
        Map<String, Object> paramMap = new HashMap<>(  );

        String type = fEnumMethod.type();
        if (HTTP_POST.equals( type )){
            for (Object obj : args){
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
                        paramMap.put( field.getName() , o );
                    }
                }
            }
            return restTemplate.postForEntity( URLBuffer.toString() , paramMap , returnType  );
        }

        if (HTTP_GET.equals( type )){
            //获取返回类型
            //获取传入的参数
            URLBuffer.append( "?" );
            for (Object obj : args){
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
        log.info( "目前仅支持GET、POST类型" );
        return null;
    }

}
