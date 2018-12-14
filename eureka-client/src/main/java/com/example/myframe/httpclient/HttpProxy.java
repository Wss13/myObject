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
           return method.invoke(proxy,args);
        }else{
            return toSent(method,args);
        }
    }
    private Object toSent(Method method,Object[] args){
        //获取请求方式
        RequestMapping requestAnnotation = method.getAnnotation(RequestMapping.class);
        //发送请求
        return MethodType.get(requestAnnotation.method()[0].toString()).send(method,args);
    }

}
