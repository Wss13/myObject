package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/10/25
 */
@SpringBootApplication
@EnableHystrixDashboard
@EnableTurbine
public class EurekaMonitorClientApplication {
    public static void main(String[] args){
        SpringApplication.run(EurekaMonitorClientApplication.class, args);
    }
}
