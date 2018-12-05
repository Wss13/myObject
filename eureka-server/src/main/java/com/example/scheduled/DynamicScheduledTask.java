package com.example.scheduled;

import com.example.util.MDA;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/28
 */

@Component
@EnableScheduling
public class DynamicScheduledTask implements SchedulingConfigurer {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(new Runnable() {
            @Override
            public void run() {
                //任务逻辑
                System.out.println("---------------start-------------------");
                    System.out.println("动态修改定时任务参数，时间表达式cron为：" + MDA.TASKTEST_SCHEDULED);
                System.out.println("当前时间为：" + sdf.format(new Date()));
                System.out.println("----------------end--------------------");
            }
        }, new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                CronTrigger cronTrigger = new CronTrigger(MDA.TASKTEST_SCHEDULED);
                Date nextExecDate = cronTrigger.nextExecutionTime(triggerContext);
                return nextExecDate;
            }
        });

    }
}
