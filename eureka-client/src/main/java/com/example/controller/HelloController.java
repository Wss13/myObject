package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private CounterService counterService;
    @RequestMapping("/hello1")
    public String greet(){
        counterService.increment("example.hello1.count");
        return "";
    }
}
