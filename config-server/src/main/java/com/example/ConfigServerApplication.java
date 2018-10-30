package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/10/26
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args){
        SpringApplication.run(ConfigServerApplication.class,args);
    }
}
