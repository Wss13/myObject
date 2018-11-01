package com.example.corecontroller;

import com.example.configure.ConfigureInit;
import com.example.configure.dto.Configuer;
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
    @RequestMapping(value = "modify")
    public void modify(Configuer configuer){
        try {
            ConfigureInit.modify(configuer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
