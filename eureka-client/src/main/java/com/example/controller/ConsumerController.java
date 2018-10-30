package com.example.controller;

import com.example.server.HelloService;
import com.example.dto.User;
import com.example.server.UserCommand;
import com.example.server.UserService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class ConsumerController {
    @Autowired
    HelloService helloService;
    @Autowired
    UserService userService;
    @Autowired
    RestTemplate restTemplate;
    @RequestMapping(value = "/ribbon-consumer",method = RequestMethod.GET)
    public String helloConsumer(){
        //post请求
        Map param = new HashMap<String,Object>();
        param.put("name","LiuMc");
        //1、请求地址。2、请求参数。3、返回类型
       // restTemplate.postForEntity("http://HELLO-SERVER/hello",param,String.class);
        return helloService.helloService();
    }
    @RequestMapping(value = "/userCommand",method = RequestMethod.GET)
    public String userCommand() throws ExecutionException, InterruptedException {
        HystrixCommand.Setter setter = HystrixCommand.Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("")).andCommandPropertiesDefaults(
                HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(5000));
        //异步处理
        Future<User> userFuture = new UserCommand(setter,restTemplate,1L).queue();
        User u = userFuture.get();
        //同步处理
//        User u = new UserCommand(setter,restTemplate,1L).execute();
        //调用的时候立即执行
        Observable<User> ho = new UserCommand(setter,restTemplate,1L).observe();
        //不会立即执行所有订阅者订阅后执行
        Observable<User> co = new UserCommand(setter,restTemplate,1L).toObservable();
        return u.getId().toString();
    }
    @RequestMapping(value = "/userCommandServer",method = RequestMethod.GET)
    public String userCommandServer() throws ExecutionException, InterruptedException {
        //同步
        User u = userService.getUserById(1L);
        //异步
//        Future<User> userFuture = userService.getUserByIdAsync("2");
//        User u = userFuture.get();
        return u.getId().toString();
    }
}
