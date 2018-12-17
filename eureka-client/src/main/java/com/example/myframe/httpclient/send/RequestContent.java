package com.example.myframe.httpclient.send;

import java.util.Map;
/**
 * Demo class
 *
 * @author liumc
 * @date 2018/12/14
 */
public class RequestContent {
    private String url;
    private Map<String,Object> requestContent;

    public RequestContent(String url, Map<String, Object> requestContent) {
        this.url = url;
        this.requestContent = requestContent;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, Object> getRequestContent() {
        return requestContent;
    }
}
