package com.example.myframe.httpclient;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/20
 */
public class HttpProxyFactory<T> {
    private final Class<T> mapperInterface;
//    private final Map<Method, MapperMethod> methodCache = new ConcurrentHashMap();

    public HttpProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public Class<T> getMapperInterface() {
        return this.mapperInterface;
    }

//    public Map<Method, MapperMethod> getMethodCache() {
//        return this.methodCache;
//    }

    protected T newInstance(HttpProxy<T> httpProxy) {
        return (T) Proxy.newProxyInstance(this.mapperInterface.getClassLoader(), new Class[]{this.mapperInterface}, httpProxy);
    }

    public T newInstance(HttpClientSession sqlSession) {
        HttpProxy<T> mapperProxy = new HttpProxy(sqlSession, this.mapperInterface);
        return this.newInstance(mapperProxy);
    }


}
