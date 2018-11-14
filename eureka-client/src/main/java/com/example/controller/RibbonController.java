package com.example.controller;

import com.example.server.RibbonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"Ribbon"})
public class RibbonController {
    @Autowired
    private RibbonService ribbonService;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private DiscoveryClient client;
    @ApiOperation(value = "获取当前当前服务配置信息", httpMethod = "GET", notes = "获取当前当前服务配置信息")
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String hi(@RequestParam(required = false,defaultValue = "forezp") String name){
        ServiceInstance instance = client.getLocalServiceInstance();
//        ServiceInstance serviceInstance =loadBalancerClient.choose("EUREKA-CLIENT");
        return "/hello,host:" + instance.getHost() + ",serverId:"
                + instance.getServiceId() + ",port:" + instance.getPort();
//        return ribbonService.hi(name);
    }
}
