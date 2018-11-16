package com.example.springmvcmock.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/16
 */
public class FactoryMock<V> {
    public Map<String, Object> getBeans() {
        return beans;
    }


    /**  存储IOC容器的MAP*/
    Map<String, Object> beans ;

    public Map<String, Object> getHandlerMap() {
        return handlerMap;
    }

    /** 存储路径和方法的映射关系*/
    Map<String, Object> handlerMap;
    public FactoryMock(HashMap hashMaph,HashMap handlerMap){
        this.beans = hashMaph;
        this.handlerMap = handlerMap;
    }
    public void beanPut(String k,V v){
        beans.put(k,v);
    }
    public V getBean(String k){
        return (V) beans.get(k);
    }
    public void handlerPut(String k,V v){
        handlerMap.put(k,v);
    }
    public V getHandler(String k){
        return (V) handlerMap.get(k);
    }
}
