package com.example.util;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/11/15
 */
public class StringUtils {
    /**
     * 首字母小写
     * @param name
     * @return
     */
    public static String LowerFrist(String name){
        char[] chars = name.toCharArray();
        /* 转大写-32*/
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
