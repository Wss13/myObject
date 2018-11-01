package com.example.demo;

import com.example.EurekaServerApplication;
import com.example.dao.ConfigureDAO;
import com.example.dto.Configuer;
import com.example.util.MDA;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/10/31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EurekaServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class ModifyTest {
    @Autowired
    private ConfigureDAO configureDAO;



    @Test
    public void test() throws Exception {

//        Class clazz = Class.forName("com.example.util.MDA");
//        Field[] fields = clazz.getFields();
//        for( Field field : fields ){
//            System.out.println( field.getName() + " " +field.get(clazz) );
//        }
    }
}
