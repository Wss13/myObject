package com.example.myframe.httpclient.send;

import com.example.myframe.httpclient.module.RemoteConfig;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

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
        StringBuffer stringBuffer = new StringBuffer();
        //获取ip和端口
        RemoteConfig remoteConfig = parentClazz.getAnnotation(RemoteConfig.class);
        String ip = remoteConfig.ip();
        String port = remoteConfig.port();
        stringBuffer.append(ip);
        if (!port.isEmpty()) {
            stringBuffer.append(":");
            stringBuffer.append(remoteConfig.port());
        }
        if (args.length != 0) {
            String requestMappingValue = requestAnnotation.value()[0];
            stringBuffer.append(recombineUrl(requestMappingValue, method, args, true));
        }

        return sendGet(stringBuffer.toString());
    }

    /**
     * 对Restful 风格处理
     *
     * @param requestMappingValue
     * @param method
     * @param args
     * @return
     */
    public String recombineUrl(String requestMappingValue, Method method, Object[] args, boolean methodType) {
        requestMappingValue += "?";
        Parameter[] parameters = method.getParameters();
        String key;
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            key = parameter.getName();
            //判断是否有注解以注解为主
            if (parameter.isAnnotationPresent(Param.class)) {
                key = parameter.getAnnotation(Param.class).value();
            }
            if (requestMappingValue.indexOf("{" + key + "}") > -1) {
                requestMappingValue = requestMappingValue.replaceAll("\\{" + key + "\\}", args[i].toString());
            } else if (methodType) {
                requestMappingValue += key + "=" + args[i];
            }
        }
        return requestMappingValue;
    }
}
