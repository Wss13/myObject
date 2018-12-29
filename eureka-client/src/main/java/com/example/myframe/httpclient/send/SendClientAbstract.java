package com.example.myframe.httpclient.send;

import com.example.myframe.httpclient.module.RemoteConfig;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/12/14
 */
public abstract class   SendClientAbstract implements SendClient {
    private static final CloseableHttpClient httpclient = HttpClients.createDefault();

    /**
     * url进行拼接
     *
     * @param method
     * @param args
     * @param methodType
     * @return
     */
    protected RequestContent recombineUrl(Method method, Object[] args, boolean methodType) {
        Map<String, Object> map = new HashMap<>(16);
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
        String requestMappingValue = "";
        if (args.length != 0) {
            requestMappingValue = requestAnnotation.value()[0];
        } else {
            return new RequestContent(requestMappingValue, map);
        }
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
            if (args[i].getClass() == String.class) {
                //如果参数命名在url上则需要替换
                if (requestMappingValue.indexOf("{" + key + "}") > -1) {
                    requestMappingValue = requestMappingValue.replaceAll("\\{" + key + "\\}", args[i].toString());
                } else if (methodType) {
                    requestMappingValue += key + "=" + args[i];
                } else {
                    map.put(key, args[i]);
                }
            }

        }
        return new RequestContent(stringBuffer.append(requestMappingValue).toString(), map);
    }

    /**
     * 发送HttpGet请求
     *
     * @param url
     * @return
     */

    @Override
    public String sendGet(String url) {

        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String result = null;
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 发送HttpPost请求，参数为map
     *
     * @param url
     * @param map
     * @return
     */
    @Override
    public String sendPost(String url, Map<String, String> map) {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        HttpPost httppost = new HttpPost(url);
        httppost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity1 = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity1);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送不带参数的HttpPost请求
     *
     * @param url
     * @return
     */
    @Override
    public String sendPost(String url) {
        HttpPost httppost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
