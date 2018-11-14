package com.example.controller;

import com.example.producer.MsgProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/13
 */
@RestController
public class QueryController {
    @Autowired
    private MsgProducer msgProducer;
    @Autowired
    private KafkaTemplate kafkaTemplate;
    @RequestMapping(value = "rabbitMsg")
    public void rabbitMsg(String msg){
        for(int index = 0;index < 10;index++){
            msgProducer.sendMsg(msg + index);
        }
    }
    @RequestMapping(value = "/sendKafka", method = RequestMethod.GET)
    public void sendKafka(HttpServletRequest request, HttpServletResponse response) {
        try {
            String message = request.getParameter("message");
            kafkaTemplate.send("myTopic", message);
        } catch (Exception e) {
        }
    }
}
