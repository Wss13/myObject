package com.example.configure.factorybean;

import java.util.HashMap;
import java.util.Map;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/05
 */
public class ModuleFactory<K,V> {
    public Map<K,V> map = new HashMap<>(16);
    public void steModule(K k,V v){
        this.map.put(k,v);
    }
    public V getMoudle(K k){
        return this.map.get(k);
    }
}
