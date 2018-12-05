package com.example.myframe.httpclient;

import com.alibaba.fastjson.JSON;
import com.example.HttpUtil;
import com.example.myframe.httpclient.module.IpConfig;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.TypeVariable;
import java.util.Map;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/19
 */
public class HttpProxy<T> implements InvocationHandler,IHttpCommon {
    private HttpClientSession httpClientSession=null;
    private final Class<T> mapperInterface;
    private HttpUitls httpUitls;
    public HttpProxy(HttpClientSession httpClientSession,Class<T> mapperInterface) {
        this.httpClientSession = httpClientSession;
        this.httpUitls = httpClientSession.getHttpUitls();
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
            StringBuffer stringBuffer = new StringBuffer();
            Class<?> parentClazz = method.getDeclaringClass();
            RequestMapping requestAnnotation = method.getAnnotation(RequestMapping.class);
            /**
             * 方法和类注解的地址进行拼接
             */
            //获取ip和端口
            stringBuffer.append(parentClazz.getAnnotation(IpConfig.class).value());

            String requestMappingValue = requestAnnotation.value()[0];

            String method1 = requestAnnotation.method()[0].toString();
            if(method1.equals("GET")){
                if(args.length != 0){
                    stringBuffer.append(recombineUrl(requestMappingValue,method,args,true));
                }
                sendGet(stringBuffer.toString());
            }else if(method1.equals("POST")){

            }
        }


        return null;
    }

    /**
     * 对Restful 风格处理
     * @param requestMappingValue
     * @param method
     * @param args
     * @return
     */
    public String recombineUrl(String requestMappingValue,Method method,Object[] args,boolean methodType){
        requestMappingValue += "?";
        Parameter[] parameters = method.getParameters();
        String key;
        for (int i = 0;i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            key = parameter.getName();
            //判断是否有注解以注解为主
            if(parameter.isAnnotationPresent(Param.class)){
                key = parameter.getAnnotation(Param.class).value();
            }
            if(requestMappingValue.indexOf("{" +key+"}")>-1){
                requestMappingValue = requestMappingValue.replaceAll("\\{" +key+"\\}",args[i].toString());
            }else if(methodType){
                requestMappingValue += key + "=" + args[i];
            }
        }
        return requestMappingValue;
    }
    /**
     * GET请求时参数和地址进行拼接
     * @param method
     * @param args
     * @return
     */
    public String getUrlSuffix(Method method,Object[] args){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("?");
        //jdk1.8关闭，默认是关闭的所以一开始用了注解的形式获取参数名称
        /** preferences-》Java Compiler->设置模块字节码版本1.8，Javac Options中的 Additional command line parameters: -parameters*/
        Parameter[] parameters = method.getParameters();
        String key;
        for (int i = 0;i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            key = parameter.getName();
            //判断是否有注解以注解为主
            if(parameter.isAnnotationPresent(Param.class)){
                key = parameter.getAnnotation(Param.class).value();
            }
            stringBuffer.append(key+"="+args[i]);
        }
        return stringBuffer.toString();
    }

    @Override
    public String sendGet(String url) {
        return httpUitls.sendGet(url);
    }

    @Override
    public String sendPost(String url, Map<String, String> map) {
        return httpUitls.sendPost(url,map);
    }

    @Override
    public String sendPost(String url) {
        return null;
    }
}
