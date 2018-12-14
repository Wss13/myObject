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
    public HttpProxy() {
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
        //获取请求方式
        RequestMapping requestAnnotation = method.getAnnotation(RequestMapping.class);
        String method1 = requestAnnotation.method()[0].toString();
        //发送请求
        return MethodType.get(method1).send(method,args);
    }

}
