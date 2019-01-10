package com.example.controller;

import com.example.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/10/25
 */
@RestController
public class RibbonController {
    @Autowired
    private RibbonService ribbonService;
    @RequestMapping(value = "/hi1", method = RequestMethod.GET)
    public String hi(@RequestParam(required = false,defaultValue = "forezp") String name){
//        ServiceInstance instance = client.getLocalServiceInstance();
//        ServiceInstance serviceInstance =loadBalancerClient.choose("EUREKA-CLIENT");
//        return "/hello,host:" + instance.getHost() + ",serverId:"
//                + instance.getServiceId() + ",port:" + instance.getPort();
        return ribbonService.hi(name);
    }
}
