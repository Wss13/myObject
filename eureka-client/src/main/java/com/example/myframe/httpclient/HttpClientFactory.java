package com.example.myframe.httpclient;

import com.example.configure.plugin.IPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/19
 */
public class HttpClientFactory<K,V> {
    private Map<K,V> map;
    public HttpClientFactory(HashMap hashMap){
        this.map = hashMap;
    }
    public void putHttpClient(K k,V clazz){
        map.put(k, (V) clazz);
    }
    public V getHttpClient(K k){
        return map.get(k);
    }
}
