package com.example.myframe.httpclient.send;


import java.lang.reflect.Method;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/12/14
 */
public class GetSendClient extends SendClientAbstract {
    @Override
    public Object send(Method method, Object[] args) {

        return sendGet(recombineUrl(method,args,false).getUrl());
    }

}
