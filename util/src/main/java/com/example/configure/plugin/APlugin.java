package com.example.configure.plugin;

import com.example.util.moudl.Plugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/15
 */
@Plugin(pluginName = "A")
public class APlugin implements IPlugin<Map>{
    @Override
    public Map printBeanName() {
        System.out.println("我是A");
        Map map = new HashMap();
        map.put("name","A");
        return map;
    }
}
