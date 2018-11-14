package com.example.util.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author    liumingchao
 */
@Component
public class FlowControlTask {
    /**
     * 固定cron配置定时任务
     */
//    @Scheduled(cron = "0/20 * * * * ?")
    public void doTask() throws Exception {
        System.out.println("20秒时间到了");
    }
}
