package com.example.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/10/31
 */
public class MDA {
    public static final String name = new String("Mike");
    public static final String TEST_NAME = new String("TEST_NAME");
    public static final String[] NAME_LIST = new String[]{"liu","lu"};
    public static final List list = new ArrayList();
    static {
        list.add("1");
    }
    public static final Map map = new HashMap();
    static {
        map.put("k","v");
    }
}
