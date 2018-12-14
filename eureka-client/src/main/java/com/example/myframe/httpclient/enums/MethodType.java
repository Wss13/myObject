package com.example.myframe.httpclient.enums;

import com.example.myframe.httpclient.send.GetSendClient;
import com.example.myframe.httpclient.send.PostSendClient;
import com.example.myframe.httpclient.send.SendClient;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/19
 */
public enum MethodType {
    GET(new GetSendClient()),
    POST(new PostSendClient());
    public SendClient sendClient;
    private static Map<String,MethodType> map = new HashMap<>();
    MethodType(SendClient sendClient){
        this.sendClient = sendClient;
    }
    static {
        for (MethodType type:MethodType.values()) {
            map.put(type.name(),type);
        }
    }
    public static SendClient get(String method){
        return map.get(method).sendClient;
    }
}
