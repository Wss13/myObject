package com.example.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/14
 */
@Component
public class KafkaProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;
    /**
     * kafka生产者
     * @param topic
     * @param massage
     */
    public void sendMsg(String topic,String massage){
        kafkaTemplate.send(topic,massage);
    }
}
