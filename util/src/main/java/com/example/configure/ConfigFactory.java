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
    @Bean
    public ModuleFactory getModuleFactory() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        ModuleFactory<String,Class> moduleFactory = new ModuleFactory();
        Set<Class<?>> classes = new Reflections("com.*").getTypesAnnotatedWith(AppModule.class);
        for(Class clazz :classes){
            AppModule t = (AppModule) clazz.getAnnotation(AppModule.class);
            moduleFactory.steModule(t.moduleName(),clazz);
        }
        return  moduleFactory ;
    }
    @Bean(initMethod = "init",destroyMethod = "destroy")
    BeanWayService beanWayService(){
        BeanWayService b = new BeanWayService();
        b.setP(111);
        return b;
    }
}
