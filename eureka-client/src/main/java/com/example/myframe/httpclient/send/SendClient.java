package com.example.myframe.httpclient.send;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/12/14
 */
public interface SendClient {
    public Object send(Method method, Object[] args);
    /**
     * 发送HttpGet请求
     * @param url
     * @return
     */
    public String sendGet(String url);

    /**
     * 发送HttpPost请求，参数为map
     * @param url
     * @param map
     * @return
     */
    public String sendPost(String url, Map<String, String> map);

    /**
     * 发送不带参数的HttpPost请求
     * @param url
     * @return
     */
    public String sendPost(String url);
}
