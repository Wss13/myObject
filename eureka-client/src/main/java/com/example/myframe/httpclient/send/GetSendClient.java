package com.example.myframe.httpclient.send;

import com.example.myframe.httpclient.module.IpConfig;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/12/14
 */
public class GetSendClient extends SendClientAbstract {
    @Override
    public Object send(Method method, Object[] args) {
        Class<?> parentClazz = method.getDeclaringClass();
        RequestMapping requestAnnotation = method.getAnnotation(RequestMapping.class);
        /**
         * 方法和类注解的地址进行拼接
         */
        //获取ip和端口
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(parentClazz.getAnnotation(IpConfig.class).value());
        String method1 = requestAnnotation.method()[0].toString();
        if(args.length != 0){

            String requestMappingValue = requestAnnotation.value()[0];
            stringBuffer.append(recombineUrl(requestMappingValue,method,args,true));
        }
        sendGet(stringBuffer.toString());
        return null;
    }
}
