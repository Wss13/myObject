package com.example.controller;

import com.example.httpclient.HttpClientTest;
import com.example.httpclient.HttpClientTest1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/10/30
 */
@RestController
@RefreshScope
public class ConfigParamController {
    @Autowired
    HttpClientTest httpClientTest;
    @Autowired
    HttpClientTest1 httpClientTest1;
    @Value("${foo}")
    String foo;
    @RequestMapping(value = "foo")
    public String hi(){
        httpClientTest.getUser1("213412","lmc");
        httpClientTest1.getUser1("adfsadfasdf");
        return foo;
    }
}
