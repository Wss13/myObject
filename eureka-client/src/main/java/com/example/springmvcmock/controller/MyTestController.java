package com.example.springmvcmock.controller;

import com.example.springmvcmock.module.MyController;
import com.example.springmvcmock.module.MyQualifier;
import com.example.springmvcmock.module.MyRequestMapping;
import com.example.springmvcmock.module.MyRequestParam;
import com.example.springmvcmock.service.IMyTestSerivce;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/16
 */
@MyController
@MyRequestMapping(value = "/myTest")
public class MyTestController {
    @MyQualifier("testService")
    public IMyTestSerivce iMyTestSerivce;
    @MyRequestMapping(value = "/query")
    public void queryTest(@MyRequestParam(value = "lmc")String name){
        iMyTestSerivce.test(name);
    }

}
