package com.example.myframe.httpclient.send;

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

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/12/14
 */
public abstract class SendClientAbstract implements SendClient{
    /**
     * 对Restful 风格处理
     * @param requestMappingValue
     * @param method
     * @param args
     * @return
     */
    public String recombineUrl(String requestMappingValue, Method method, Object[] args, boolean methodType){
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
    private static final CloseableHttpClient httpclient = HttpClients.createDefault();

    /**
     * 发送HttpGet请求
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
