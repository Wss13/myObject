package com.example.springmvcmock.module;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/16
 */
/* 设置注解类型*/
@Target(ElementType.TYPE)
/* 设置生命周期*/
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyController {
    String value() default "";
}
