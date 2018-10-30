package com.example.hystrix;

import com.example.client.EurekaClientFeign;
import org.springframework.stereotype.Component;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/10/25
 */
@Component
public class HiHystrix implements EurekaClientFeign {
    int i = 0;
    @Override
    public String sayHiFromClientEureka(String name) {
        return "hi," + name + ",sorry,error!"+i++;
    }
}
