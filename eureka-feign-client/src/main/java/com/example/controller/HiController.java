package com.example.controller;

import com.example.server.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/10/24
 */
@RestController
public class HiController {
    @Autowired
    private HiService hiService;
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String sayHi(@RequestParam(defaultValue = "liumc",required = false) String name){
        return hiService.sayHi(name);
    }
}
