package com.example.client;

import com.example.hystrix.HiHystrix;
import com.example.feignconfig.FeignConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/10/24
 */
@FeignClient(value = "eureka-client",configuration = FeignConfig.class,fallback = HiHystrix.class)
public interface EurekaClientFeign {
    /**
     * 请求到eureka-client的接口
     * @param name
     * @return
     */
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHiFromClientEureka(@RequestParam(value="name") String name);
}
