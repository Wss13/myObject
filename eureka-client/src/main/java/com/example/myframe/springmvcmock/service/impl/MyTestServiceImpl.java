package com.example.myframe.springmvcmock.service.impl;

import com.example.myframe.springmvcmock.module.MyService;
import com.example.myframe.springmvcmock.service.IMyTestSerivce;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/16
 */
@MyService(value = "testService")
public class MyTestServiceImpl implements IMyTestSerivce {
    @Override
    public void test(String name) {
        System.out.println(name);
    }
}
