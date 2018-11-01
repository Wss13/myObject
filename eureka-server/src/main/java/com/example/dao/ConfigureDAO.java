package com.example.dao;

import com.example.dto.Configuer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/10/31
 */
@Mapper
public interface ConfigureDAO {
    public List<Configuer> queryAllConfigure();
}
