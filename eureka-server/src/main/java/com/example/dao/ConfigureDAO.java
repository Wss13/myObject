package com.example.dao;

import com.example.config.Page;
import com.example.dto.Configuer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/10/31
 */
@Mapper
public interface ConfigureDAO {
    public List<Configuer> queryAllConfigure(Page page);
}
