package com.example.myframe.httpclient;

import java.lang.reflect.Proxy;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/19
 */
public class HttpClientSession {
    HttpUitls httpUitls = new HttpUitls();

//    public <T> T getMapper(Class<T> clazz){
//        return (T)Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz}, new HttpProxy<>(this));
//    }
    public HttpUitls getHttpUitls(){
        return httpUitls;
    }

}
