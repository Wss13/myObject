package com.example.myframe.springmvcmock.service.impl;

import com.example.myframe.springmvcmock.module.MyService;
import com.example.myframe.springmvcmock.service.ArgumentResolver;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/16
 */
@MyService("httpServletResponseArgumentResolver")
public class HttpServletResponseArgumentResolver implements ArgumentResolver {
    @Override
    public boolean support(Class<?> type, int paramIndex, Method method) {
        return ServletResponse.class.isAssignableFrom(type);
    }

    @Override
    public Object argumentResolver(HttpServletRequest request, HttpServletResponse response, Class<?> type, int paramIndex, Method method) {
        return response;
    }
}
