package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/*支持多种注册
@EnableDiscoveryClient*/
/*支持多Enable注册*/
/*熔断器*/
@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
@EnableSwagger2
/**
 * Demo class
 *
 * @author liumc
 * @date 2018/10/25
 */
public class EurekaClientApplication {
    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
    public static void main(String[] args){
        SpringApplication.run(EurekaClientApplication.class);
    }

}
