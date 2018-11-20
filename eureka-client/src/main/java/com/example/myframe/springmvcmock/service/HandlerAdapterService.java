package com.example.myframe.springmvcmock.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/16
 */
public interface HandlerAdapterService {
    public Object[] handle(HttpServletRequest req, HttpServletResponse resp,
                           Method method, Map<String, Object> beans);
}
