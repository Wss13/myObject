package com.example.service.configure;

import com.example.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/06
 */
@Configuration
@ComponentScan("com.example")
public class RmiProxyFactory {
//    @Bean
    public RmiProxyFactoryBean rmiProxyFactoryBean() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://127.0.0.1:1233/hello");
        rmiProxyFactoryBean.setServiceInterface(HelloService.class);
        return rmiProxyFactoryBean;
    }
}
