package com.example.myframe.springmvcmock.configinit;

import com.example.configure.factorybean.ModuleFactory;
import com.example.myframe.springmvcmock.factory.FactoryMock;
import com.example.myframe.springmvcmock.module.MyController;
import com.example.myframe.springmvcmock.module.MyQualifier;
import com.example.myframe.springmvcmock.module.MyRequestMapping;
import com.example.myframe.springmvcmock.module.MyService;
import com.example.util.moudl.AppModule;
import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/16
 */
@Configuration
public class SpringMvcMock {
    @Bean
    public FactoryMock factoryMock() throws IllegalAccessException, InstantiationException {

        FactoryMock<Object> factoryMock = new FactoryMock<>(new HashMap(16),new HashMap(16));
        /** 扫描注解实例化*/
        doInstance(factoryMock);
        /** 将控制层所注解的服务利用反射的机制设置进去*/
        doIoc(factoryMock);
        /** 4.建立path与method的映射关系*/
        handlerMapping(factoryMock);
        return factoryMock;
    }

    /**
     * 扫描注解实例化
     * @param factoryMock
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private void doInstance(FactoryMock<Object> factoryMock) throws IllegalAccessException, InstantiationException {
        Reflections reflections = new Reflections("com.example.myframe.springmvcmock");
        Set<Class<?>> myControllerList = reflections.getTypesAnnotatedWith(MyController.class);
        Set<Class<?>> myServiceList = reflections.getTypesAnnotatedWith(MyService.class);
        for (Class clazzController:myControllerList) {
            MyRequestMapping myRequestMapping = (MyRequestMapping) clazzController.getAnnotation(MyRequestMapping.class);
            factoryMock.beanPut(myRequestMapping.value(),clazzController.newInstance());
        }
        for (Class clazzService:myServiceList) {
            MyService myService = (MyService) clazzService.getAnnotation(MyService.class);
            Object instance = clazzService.newInstance();
            /* myService有value的话用value注册到模拟工厂否则用类名*/
            if(!"".equals(myService.value().trim())){
                factoryMock.beanPut(myService.value(),instance);
            }else{
                Class<?>[] classes = clazzService.getInterfaces();
                for (Class i:classes) {
                    factoryMock.beanPut(i.getName(),instance);
                }

            }
        }
    }

    /**
     * 将控制层所注解的服务利用反射的机制设置进去
     * @param factoryMock
     */
    private void doIoc(FactoryMock<Object> factoryMock) throws IllegalAccessException {
        Map beans = factoryMock.getBeans();
        if(beans.isEmpty()){
            return;
        }
        Iterator<Map.Entry<String, Object>> it = beans.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String,Object> entry = it.next();
            Object instance = entry.getValue();
            Class<?> clazz = instance.getClass();
            if(!clazz.isAnnotationPresent(MyController.class)){
                continue;
            }
            Field[] fields = clazz.getFields();
            for (Field field:fields) {
                if(!field.isAnnotationPresent(MyQualifier.class)){
                    continue;
                }
                /** 获取带有MyQualifier注解的字段*/
                MyQualifier myQualifier = field.getAnnotation(MyQualifier.class);
                String key = myQualifier.value();
                if("".equals(key)){
                    key = field.getType().getName();
                }
                field.setAccessible(true);
                if(!field.isAccessible()){
                    field.setAccessible(true);
                }
                field.set(instance,beans.get(key));
            }

        }
    }

    /**
     *
     * @param factoryMock
     */
    private void handlerMapping(FactoryMock<Object> factoryMock){
        Map beans = factoryMock.getBeans();
        if(beans.isEmpty()){
            return;
        }
        Iterator<Map.Entry<String, Object>> it = beans.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            Object instance = entry.getValue();
            Class<?> clazz = instance.getClass();
            if(!clazz.isAnnotationPresent(MyController.class)){
                continue;
            }
            MyRequestMapping clazzRm = clazz.getAnnotation(MyRequestMapping.class);
            String clazzPath = clazzRm.value();
            if(!clazz.isAnnotationPresent(MyController.class)){
                continue;
            }
            /** 获取该实例的所有方法*/
            Method[] methods = clazz.getMethods();
            for (Method method:methods) {
                if(!method.isAnnotationPresent(MyRequestMapping.class)){
                    continue;
                }
                MyRequestMapping methodRm = method.getAnnotation(MyRequestMapping.class);
                String methodPath = methodRm.value();
                /** 避免requestMapping 没有写/*/
                factoryMock.handlerPut(("/"+clazzPath + "/" +methodPath).replaceAll("/+",""),method);
            }
        }
    }
}
