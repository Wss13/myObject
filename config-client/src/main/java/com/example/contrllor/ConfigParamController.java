package com.example.contrllor;

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
    @Value("${foo}")
    String foo;
    @RequestMapping(value = "foo")
    public String hi(){
        return foo;
    }
}
