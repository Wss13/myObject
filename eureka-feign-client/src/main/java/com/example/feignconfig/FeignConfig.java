package com.example.feignconfig;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author    liumingchao
 */
@Configuration
public class FeignConfig {
    /**重试接口*/
    @Bean
    public Retryer feignRetryer(){
        return new Retryer.Default(100,SECONDS .toMillis(1), 5);
    }
}
