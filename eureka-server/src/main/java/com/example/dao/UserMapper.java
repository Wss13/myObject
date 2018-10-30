package com.example.dao;

import com.example.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Mapper
public interface UserMapper {
    public Integer isPassLoginCheck(Map map);
}
