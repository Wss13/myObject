package com.example.myframe.httpclient;

import org.springframework.beans.factory.FactoryBean;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/20
 */
public class HttpBuildFactoryBean<T> implements FactoryBean<T> {

    public Class<T> getMapperInterface() {
        return mapperInterface;
    }

    public void setMapperInterface(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public HttpProxyFactory<T> getHttpProxyFactory() {
        return httpProxyFactory;
    }

    public void setHttpProxyFactory(HttpProxyFactory<T> httpProxyFactory) {
        this.httpProxyFactory = httpProxyFactory;
    }

    public HttpClientSession getHttpClientSession() {
        return httpClientSession;
    }

    public void setHttpClientSession(HttpClientSession httpClientSession) {
        this.httpClientSession = httpClientSession;
    }

    private  Class<T> mapperInterface;
    private  HttpProxyFactory<T> httpProxyFactory;
    private  HttpClientSession httpClientSession;
    @Override
    public T getObject() throws Exception {
        return httpProxyFactory.newInstance(httpClientSession);
    }

    @Override
    public Class<?> getObjectType() {
        return this.mapperInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
