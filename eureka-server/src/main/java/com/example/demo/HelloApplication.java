package com.example.demo;

import com.example.dao.ConfigureDAO;
import com.example.dao.UserMapper;
import com.example.dto.User;
import com.example.util.redis.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

@RestController
@Api(tags = {"测试"})
public class HelloApplication {
    private final Logger logger = Logger.getLogger(String.valueOf(getClass()));
    @Autowired
    private DiscoveryClient client;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ConfigureDAO configureDAO;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() throws InterruptedException {
        ServiceInstance instance = client.getLocalServiceInstance();
        int sleepTime = new Random().nextInt(3000);
        logger.info("sleepTime:" + sleepTime);
        Thread.sleep(sleepTime);
        logger.info("/hello,host:" + instance.getHost() + ",serverId:"
                + instance.getServiceId() + ",port:" + instance.getPort());
        return "Hello World!";
    }

    @ApiOperation(value = "用户信息", httpMethod = "GET", notes = "用户信息")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public User getUser(HttpServletRequest request) throws InterruptedException {
        String id = request.getParameter("id") == null ? "0" : request.getParameter("id");
        return new User(Long.parseLong(id));
    }

    @RequestMapping(value = "/users1", method = RequestMethod.GET)
    @ResponseBody
    public User getUser1(HttpServletRequest request) throws InterruptedException {
        Map map = new HashMap(16);
        map.put("email", "user");
        map.put("password", "123");
        configureDAO.queryAllConfigure();
        userMapper.isPassLoginCheck(map);
        redisUtils.hget("lmc", "");
        String id = request.getParameter("id") == null ? "0" : request.getParameter("id");
        return new User(Long.parseLong(id));
    }
}
