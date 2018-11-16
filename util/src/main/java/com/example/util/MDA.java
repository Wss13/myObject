package com.example.util;


import com.example.util.moudl.AppModule;

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
@AppModule(moduleName = "utilMDA")
public class MDA {
    @AppModule(ifChange = false)
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
    public static final long TEST_LONG = new Long(1L);
    public static final int TEST_INT = new Integer(1);
    public static final boolean TEST_BOOLEAN = true;
    public static final Boolean TEST_BOOLEAN_X = true;
    public static final Long TEST_LONG_X = new Long(1L);
    public static final Integer TEST_INT_X = new Integer(1);
    public static final String TEST_STRING_X = "1";
    public static final long TEST_LONG_S = 1;
    public static final int TEST_INT_S = 1;
}
