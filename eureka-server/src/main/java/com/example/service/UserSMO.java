package com.example.service;

import com.example.dto.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/12/24
 */
public interface UserSMO {
    /**
     * 批量插入
     */
    public void insertBatchUser();
    public void insertBatchUserToSqlSession();
}
