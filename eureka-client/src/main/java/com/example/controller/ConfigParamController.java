package com.example.controller;

import com.example.httpclient.HttpClientTest;
import com.example.myframe.httpclient.HttpClientFactory;
import com.example.myframe.httpclient.module.IpAutowired;
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
@IpAutowired
@RestController
@RefreshScope
public class ConfigParamController {
/*    @Autowired
    HttpClientFactory httpClientFactory;*/
    @Autowired
    HttpClientTest httpClientTest;
    @Value("${foo}")
    String foo;
    @RequestMapping(value = "foo")
    public String hi(){
//        HttpClientTest t = (HttpClientTest) httpClientFactory.getHttpClient(HttpClientTest.class);
        httpClientTest.getUser1("213412");
        return foo;
    }
}
