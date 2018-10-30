package com.example.server;

import com.example.dto.User;
import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

public class UserCommand extends HystrixCommand<User> {
    private RestTemplate restTemplate;
    private Long id;
    public UserCommand(Setter setter, RestTemplate restTemplate, Long id) {
        super(setter);
        this.restTemplate = restTemplate;
        this.id = id;
    }
    @Override
    protected User run() throws Exception {
        //因为是new 出来的 所以 用ribbon 的形式请求不通
//        return restTemplate.getForObject("http://localhost:1112/users?id={1}",User.class,id);
        return restTemplate.getForObject("http://HELLO-SERVER/users?id={1}",User.class,id);

    }
    @Override
    protected User getFallback(){
        return new User(2L);
    }
}
