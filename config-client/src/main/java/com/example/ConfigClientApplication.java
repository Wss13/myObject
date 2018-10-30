package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/10/26
 */
@SpringBootApplication
@RestController
public class ConfigClientApplication {
    public static void main(String[] args){
        SpringApplication.run(ConfigClientApplication.class,args);
    }
    @Value("${foo}")
    String foo;
    @RequestMapping(value = "foo")
    public String hi(){
        return foo;
    }
}
