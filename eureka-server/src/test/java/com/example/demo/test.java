package com.example.demo;

import com.example.EurekaServerApplication;
import com.example.util.redis.RedisUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/10/29
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=EurekaServerApplication.class)// 指定spring-boot的启动类
//@SpringApplicationConfiguration(classes = WebAppConfig.class)
public class test {
    @Autowired
    RedisUtils redisUtils;
    @Test
    @Rollback
    public void test() throws Exception{
        redisUtils.hget("lmc", "");
        //断言
        Assert.assertEquals(1,1);
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        Connection ct=DriverManager.getConnection("jdbc:microsoft:sqlserver://192.168.1.90:1433;databaseName=cec","sa","sasasa");
//        Statement sm=ct.createStatement();
//        ResultSet rs=sm.executeQuery("select  * from ccc");
    }
}
