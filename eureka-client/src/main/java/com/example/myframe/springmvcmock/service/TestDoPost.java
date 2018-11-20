package com.example.myframe.springmvcmock.service;

import com.example.myframe.springmvcmock.factory.FactoryMock;
import com.example.myframe.springmvcmock.module.MyController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Demo class
 *模拟servlet类
 * @author liumc
 * @date 2018/11/16
 */
@Component
public class TestDoPost {
    @Autowired
    FactoryMock factoryMock;
    private void mockDoPost(HttpServletRequest request, HttpServletResponse response,String path) throws InvocationTargetException, IllegalAccessException {
        Map handler = factoryMock.getHandlerMap();
        Map beans = factoryMock.getBeans();
        Method method = (Method) handler.get(path);
        MyController instance = (MyController) beans.get("/" + path.split("/")[1]);
        // 处理参数
        HandlerAdapterService ha = (HandlerAdapterService) beans.get("customHandlerAdapter");
        Object[] args = ha.handle(request, response, method, beans);
        method.invoke(instance,args);
    }
}
