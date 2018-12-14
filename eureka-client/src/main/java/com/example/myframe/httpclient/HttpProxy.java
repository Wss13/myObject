package com.example.myframe.httpclient;

import com.example.myframe.httpclient.enums.MethodType;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/19
 */
public class HttpProxy<T> implements InvocationHandler {
    private final Class<T> mapperInterface;
    public HttpProxy(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    /**
     * Http接口动态代理实现方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            method.invoke(proxy,args);
        }else{
            toSent(method,args);
        }


        return null;
    }
    public Object toSent(Method method,Object[] args){

        Class<?> parentClazz = method.getDeclaringClass();
        RequestMapping requestAnnotation = method.getAnnotation(RequestMapping.class);
        /**
         * 方法和类注解的地址进行拼接
         */
        //获取ip和端口

        String method1 = requestAnnotation.method()[0].toString();
        MethodType.get(method1).send(method,args);
        return null;
    }

}
