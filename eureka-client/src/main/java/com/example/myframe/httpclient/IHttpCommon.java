package com.example.myframe.httpclient;

import java.util.Map;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/20
 */
public interface IHttpCommon {
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
