package com.example.myframe.springmvcmock.controller;

import com.example.myframe.springmvcmock.module.MyController;
import com.example.myframe.springmvcmock.module.MyQualifier;
import com.example.myframe.springmvcmock.module.MyRequestMapping;
import com.example.myframe.springmvcmock.module.MyRequestParam;
import com.example.myframe.springmvcmock.service.IMyTestSerivce;

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
    public void queryTest(@MyRequestParam(value = "name")String name){
        iMyTestSerivce.test(name);
    }

}
