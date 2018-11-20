package com.example.myframe.springmvcmock.service.impl;

import com.example.myframe.springmvcmock.module.MyRequestParam;
import com.example.myframe.springmvcmock.module.MyService;
import com.example.myframe.springmvcmock.service.ArgumentResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/16
 */
@MyService("requestParamArgumentResolver")
public class RequestParamArgumentResolver implements ArgumentResolver {
    @Override
    public boolean support(Class<?> type, int paramIndex, Method method) {
        Annotation[][] an = method.getParameterAnnotations();
        Annotation[] paramAns = an[paramIndex];

        for (Annotation paramAn : paramAns) {
            //判断传进的paramAn.getClass()是不是 CustomRequestParam 类型
            if (MyRequestParam.class.isAssignableFrom(paramAn.getClass())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Object argumentResolver(HttpServletRequest request, HttpServletResponse response, Class<?> type, int paramIndex, Method method) {
        //获取当前方法的参数
        Annotation[][] an = method.getParameterAnnotations();
        Annotation[] paramAns = an[paramIndex];

        for (Annotation paramAn : paramAns) {
            //判断传进的paramAn.getClass()是不是 CustomRequestParam 类型
            if (MyRequestParam.class.isAssignableFrom(paramAn.getClass())) {
                MyRequestParam cr = (MyRequestParam) paramAn;
                String value = cr.value();

                return request.getParameter(value);
            }
        }
        return null;
    }
}
