package com.example.configure.plugin;

import com.example.configure.factorybean.ModuleFactory;
import com.example.util.moudl.AppModule;
import com.example.util.moudl.Plugin;
import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/15
 */
@Configuration
public class PluginInit {
    @Bean
    public PluginFactory pluginFactory() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        PluginFactory pluginFactory = new PluginFactory(new HashMap(16));
        Set<Class<?>> classes = new Reflections("com.*").getTypesAnnotatedWith(Plugin.class);
        for(Class clazz :classes){
            Plugin t = (Plugin) clazz.getAnnotation(Plugin.class);
            // 第一个参数是传进去的方法名称，第二个参数是 传进去的类型；
            Method m = t.getClass().getMethod("pluginName");
            pluginFactory.setPublic(m.invoke(t).toString(), (IPlugin) clazz.newInstance());
        }
        return pluginFactory;
    }
}
