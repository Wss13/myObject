package com.example;

import com.example.service.HelloService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableEurekaServer
@SpringBootApplication
@EnableSwagger2
//@MapperScan("com.**.dao*")
public class EurekaServerApplication {
    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
    public static void main(String[] args){
        ConfigurableApplicationContext run = SpringApplication.run(EurekaServerApplication.class,args);
//        //获取方法
//        HelloService bean = run.getBean(HelloService.class);
//        //调用接口方法，实际上是远程方法
//        System.out.println(bean.say("hf"));
    }
}
