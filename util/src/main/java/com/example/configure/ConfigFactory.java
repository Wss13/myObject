package com.example.configure;

import com.example.configure.factorybean.ModuleFactory;
import com.example.servicetest.BeanWayService;
import com.example.util.moudl.AppModule;
import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/05
 */
@Configuration
public class ConfigFactory {
    @Bean(initMethod="init")
    public ModuleFactory getModuleFactory() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        ModuleFactory moduleFactory = new ModuleFactory();
        Map<String,Class> moduleMap = new HashMap<String,Class>();
        Set<Class<?>> classes = new Reflections("com.*").getTypesAnnotatedWith(AppModule.class);
        for(Class clazz :classes){
            AppModule t = (AppModule) clazz.getAnnotation(AppModule.class);
            // 第一个参数是传进去的方法名称，第二个参数是 传进去的类型；
            Method m = t.getClass().getMethod("moduleName");
            moduleMap.put(m.invoke(t).toString(),clazz);
        }
        moduleFactory.setModuleMap(moduleMap);
        return  moduleFactory ;
    }
    @Bean(initMethod = "init",destroyMethod = "destroy")
    BeanWayService beanWayService(){
        BeanWayService b = new BeanWayService();
        b.setP(111);
        return b;
    }
}
