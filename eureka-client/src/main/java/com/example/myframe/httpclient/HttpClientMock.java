package com.example.myframe.httpclient;

import com.example.myframe.httpclient.module.RemoteConfig;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/19
 */
@Configuration
public class HttpClientMock {
    @Autowired
    ConfigurableBeanFactory beanFactory;

    @Bean
    public ConfigurableBeanFactory httpClientFactory() {
        doIoc();
        /** 4.建立path与method的映射关系*/
        return beanFactory;
    }

    /**
     * 生成一个接口代理工厂
     */
    public void doIoc() {
        Reflections reflections = new Reflections("com.example.*");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(RemoteConfig.class);
        for (Class<?> clazz : classes) {
            HttpProxyFactory httpProxyFactory = new HttpProxyFactory(clazz);
            beanFactory.registerSingleton(clazz.getName(), httpProxyFactory.newInstance());
        }
    }

}
