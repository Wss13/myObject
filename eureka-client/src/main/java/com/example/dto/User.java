package com.example.dto;

public class User {
    public User(Long id){
        this.id = id;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;
}
