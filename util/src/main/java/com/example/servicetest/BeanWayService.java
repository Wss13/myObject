package com.example.servicetest;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/02
 */
public class BeanWayService {
    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    int p;
    public void init(){
        System.out.println("@Bean-init-method//1在构造函数执行完之后执行");
    }
    public BeanWayService() {
        super();
        System.out.println("初始化构造函数-BeanWayService");
    }
    public void destroy(){
        System.out.println("@Bean-destory-method//2在bean销毁之前执行");
    }
}
