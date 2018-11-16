package com.example.springmvcmock.service.impl;

import com.example.springmvcmock.module.MyService;
import com.example.springmvcmock.service.ArgumentResolver;
import com.example.springmvcmock.service.HandlerAdapterService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/16
 */
@MyService("customHandlerAdapter")
public class CustomHandlerAdapter implements HandlerAdapterService {
    @Override
    public Object[] handle(HttpServletRequest req, HttpServletResponse resp, Method method, Map<String, Object> beans) {
        Class<?>[] paramClazzs = method.getParameterTypes();
        Object[] args = new Object[paramClazzs.length];
        // 定义一个ArgumentResolver实现类的Map
        Map<String, Object> argumentResolvers = getBeansOfType(beans, ArgumentResolver.class);
        //定义参数索引
        int paramIndex = 0;
        //定义数组下标索引
        int i = 0;
        for(Class<?> paramClazz: paramClazzs) {
            //哪个参数对应了哪个参数解析类,用策略模式来找
            for (Map.Entry<String, Object> entry : argumentResolvers.entrySet()) {
                ArgumentResolver ar = (ArgumentResolver)entry.getValue();

                if (ar.support(paramClazz, paramIndex, method)) {
                    args[i++] = ar.argumentResolver(req,
                            resp,
                            paramClazz,
                            paramIndex,
                            method);
                }
            }
            paramIndex++;
        }

        return args;
    }
    private Map<String, Object> getBeansOfType(Map<String, Object> beans,
                                               Class<ArgumentResolver> intfType) {
        Map<String, Object> resultBeans = new HashMap<>();
        for(Map.Entry<String, Object> map: beans.entrySet()) {
            // 获取满足ArgumentResolver接口的bean
            Class<?>[] intfs = map.getValue().getClass().getInterfaces();

            if(intfs != null && intfs.length >0) {
                for(Class<?> intf: intfs) {
                    // 将满足的bean存储在resultBeans中
                    if(intf.isAssignableFrom(intfType)) {
                        resultBeans.put(map.getKey(), map.getValue());
                    }
                }
            }
        }
        return resultBeans;
    }
}
