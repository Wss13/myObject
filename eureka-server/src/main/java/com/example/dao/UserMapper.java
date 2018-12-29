package com.example.dao;

import com.example.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
@Mapper
public interface UserMapper {
    /**
     * 密码校验
     * @param map
     * @return
     */
    public Integer isPassLoginCheck(Map map);
    /**
     * 批量插入
     * @param userList
     */
    public void insertBatchUser(@Param(value = "userList") List<User> userList);
    public void insertUser(@Param(value = "user") User user);

}
