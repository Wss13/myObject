package com.example.myframe.httpclient;

import com.example.myframe.httpclient.module.IpAutowired;
import com.example.myframe.httpclient.module.IpConfig;
import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Set;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/19
 */
@Configuration
public class httpClientMock {


    @Bean
    public HttpClientFactory httpClientFactory(){
        HttpClientFactory httpClientFactory = new HttpClientFactory(new HashMap(16));
        doIoc(httpClientFactory);
        /** 4.建立path与method的映射关系*/
//        handlerMapping(httpClientFactory);
        return httpClientFactory;
    }

    /**
     * 生成一个接口代理工厂
     * @param httpClientFactory
     */
    public void doIoc(HttpClientFactory httpClientFactory){
        Reflections reflections = new Reflections("com.example.httpclient");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(IpConfig.class);
        HttpClientSession httpClientSession = new HttpClientSession();
        for (Class<?> clazz:classes) {
            httpClientFactory.putHttpClient(clazz,httpClientSession.getMapper(clazz));
        }
    }

    /**
     * 注册到容器 目前每有实现不懂为什么 在想其他方法
     * @param httpClientFactory
     */
    public void handlerMapping(HttpClientFactory httpClientFactory){
        Reflections reflections = new Reflections("com.example");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(IpAutowired.class);
        for (Class<?> clazz:classes) {
            Field[] fields = clazz.getFields();
            for (Field field:fields) {
                if(!field.isAnnotationPresent(IpAutowired.class)){
                    continue;
                }
                field.setAccessible(true);
                try {
                    field.set(clazz.newInstance(),httpClientFactory.getHttpClient(field.getType().getName()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
