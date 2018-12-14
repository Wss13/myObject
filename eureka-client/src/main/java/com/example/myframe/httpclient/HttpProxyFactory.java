package com.example.myframe.httpclient;

import java.lang.reflect.Proxy;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/20
 */
public class HttpProxyFactory<T> {
    private final Class<T> mapperInterface;

    public HttpProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }


    protected T newInstance(HttpProxy<T> httpProxy) {
        return (T) Proxy.newProxyInstance(this.mapperInterface.getClassLoader(), new Class[]{this.mapperInterface}, httpProxy);
    }

    public T newInstance() {
        HttpProxy<T> mapperProxy = new HttpProxy();
        return this.newInstance(mapperProxy);
    }


}
