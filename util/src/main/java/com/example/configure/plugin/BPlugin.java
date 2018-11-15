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
@Plugin(pluginName = "B")
public class BPlugin implements IPlugin<Map> {
    @Override
    public Map printBeanName() {
        System.out.println("我是B");
        Map map = new HashMap();
        map.put("name","A");
        return map;
    }
}
