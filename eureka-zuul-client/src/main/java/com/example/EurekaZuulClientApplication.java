package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/10/25
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class EurekaZuulClientApplication {
    public static void main(String[] args){
        SpringApplication.run(EurekaZuulClientApplication.class);
    }
}
