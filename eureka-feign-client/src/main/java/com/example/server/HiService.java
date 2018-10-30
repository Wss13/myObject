package com.example.server;

import com.example.client.EurekaClientFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/10/24
 */
@Service
public class HiService {
    @Autowired
    private EurekaClientFeign eurekaClientFeign;
    public String sayHi(String name){
        return eurekaClientFeign.sayHiFromClientEureka(name);
    }
}
