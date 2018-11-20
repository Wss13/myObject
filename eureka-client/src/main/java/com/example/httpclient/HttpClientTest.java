package com.example.httpclient;

import com.example.dto.User;
import com.example.myframe.httpclient.module.IpConfig;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/20
 */
@IpConfig("http://127.0.0.1:1111")
public interface HttpClientTest {
    @RequestMapping(value = "/users1", method = RequestMethod.GET)
    public User getUser1(@Param("id") String id);
}
