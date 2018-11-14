package com.example.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;
@Mapper
public interface UserMapper {
    public Integer isPassLoginCheck(Map map);
}
