package com.example.corecontroller;

import com.example.configure.ConfigureInit;
import com.example.configure.dto.Configuer;
import com.example.servicetest.BeanWayService;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/01
 */
@RestController
public class callConfigMethod {
    @Autowired
    BeanWayService beanWayService;
    @RequestMapping(value = "modify")
    public void modify(Configuer configuer){
        try {
            new Reflections("cn.*", new MethodAnnotationsScanner(), new TypeAnnotationsScanner(), new SubTypesScanner());
            ConfigureInit.modify(configuer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
