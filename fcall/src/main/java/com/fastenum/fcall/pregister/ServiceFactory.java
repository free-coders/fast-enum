package com.fastenum.fcall.pregister;

import com.fastenum.fcall.handler.FEnumClientHandler;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Description : 工厂bean负责把接口生成代理类
 * @Author : zhangMing
 * @Date : Created in 11:35 2020-09-30
 */
public class ServiceFactory<T> implements FactoryBean<T> {

    private Class<T> interfaceType;

    private RestTemplate restTemplate;

    public ServiceFactory(Class<T> interfaceType ,RestTemplate restTemplate) {
        this.interfaceType = interfaceType;
        this.restTemplate = restTemplate;
    }

    /**
     * 获取bean
     * @return
     * @throws Exception
     */
    @Override
    public T getObject() throws Exception {
        //这里主要是创建接口对应的实例，便于注入到spring容器中
        InvocationHandler handler = new FEnumClientHandler<>( interfaceType , restTemplate );
        return (T) Proxy.newProxyInstance( interfaceType.getClassLoader(),
                new Class[]{interfaceType}, handler );
    }

    @Override
    public Class<T> getObjectType() {
        return interfaceType;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
