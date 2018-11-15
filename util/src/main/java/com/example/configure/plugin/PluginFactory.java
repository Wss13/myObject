package com.example.configure.plugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/15
 */
public class PluginFactory<K,V> {
    private Map<K,V> map;
    public PluginFactory(HashMap hashMap){
        this.map = hashMap;
    }
    public void setPublic(K k,IPlugin iPlugin){
        map.put(k,(V)iPlugin);
    }
    public V getPublic(K k){
        return map.get(k);
    }
}
