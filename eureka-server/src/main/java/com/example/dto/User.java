package com.example.dto;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/13
 */
public class User {
    public User(long l) {
        this.id = l;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;
}
