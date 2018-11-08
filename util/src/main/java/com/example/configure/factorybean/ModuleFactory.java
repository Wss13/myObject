package com.example.configure.factorybean;

import java.util.HashMap;
import java.util.Map;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/05
 */
public class ModuleFactory {
    public Map<String, Class> getModuleMap() {
        return moduleMap;
    }

    public void setModuleMap(Map<String, Class> moduleMap) {
        this.moduleMap = moduleMap;
    }
    public void init(){
        if(this.moduleMap==null){
            this.moduleMap = new HashMap<String, Class>();
        }
    }

    private Map<String,Class> moduleMap;
}
