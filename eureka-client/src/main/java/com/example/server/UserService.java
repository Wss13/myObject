package com.example.server;

import com.example.dto.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;
@Service
public class UserService {
    @Autowired
    private RestTemplate restTemplate;
    //同步执行
    @HystrixCommand(fallbackMethod = "defaultUser")
    public User getUserById(Long id){
        return restTemplate.getForObject("http://HELLO-SERVER/users1?id={1}",User.class,id);
    }
    //异步执行
    @HystrixCommand(fallbackMethod = "defaultUser")
    public Future<User> getUserByIdAsync(final String id){
        return new AsyncResult<User>() {
            @Override
            public User invoke() {
                return restTemplate.getForObject("http://HELLO-SERVER/users?id={1}",User.class,id);
            }
        };
    }
    @HystrixCommand(fallbackMethod = "defaultUserSec")
    public User defaultUser(Long id){
        return new User(3L);
    }
    public User defaultUserSec(Long id){
        return new User(4L);
    }
}
