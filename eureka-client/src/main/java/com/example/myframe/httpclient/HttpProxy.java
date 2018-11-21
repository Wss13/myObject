package com.example.myframe.httpclient;

import com.alibaba.fastjson.JSON;
import com.example.HttpUtil;
import com.example.myframe.httpclient.module.IpConfig;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
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
            stringBuffer.append(parentClazz.getAnnotation(IpConfig.class).value());
            stringBuffer.append(requestAnnotation.value()[0]);
            String method1 = requestAnnotation.method()[0].toString();
            if(method1.equals("GET")){
                if(args.length != 0){
                    stringBuffer.append(doGet(method,args));
                }
                sendGet(stringBuffer.toString());
            }else if(method1.equals("POST")){

            }
        }


        return null;
    }

    /**
     * GET请求时参数和地址进行拼接
     * @param method
     * @param args
     * @return
     */
    public String doGet(Method method,Object[] args){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("?");
        Annotation[][] an = method.getParameterAnnotations();
        for(int i = 0;i<args.length;i++){
            Annotation[] paramAns = an[i];
            for (Annotation param:paramAns) {
                if (Param.class.isAssignableFrom(param.getClass())) {
                    Param cr = (Param) param;
                    String value = cr.value();
                    stringBuffer.append(value+"="+args[i]);
                }
            }

        }
        return stringBuffer.toString();
    }

    @Override
    public String sendGet(String url) {
        return httpUitls.sendGet(url);
    }

    @Override
    public String sendPost(String url, Map<String, String> map) {
        return null;
    }

    @Override
    public String sendPost(String url) {
        return null;
    }
}
