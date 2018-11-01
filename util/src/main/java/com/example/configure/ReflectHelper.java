package com.example.configure;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/10/31
 */
public class ReflectHelper<T> {
    /**
     *
     * @param clazz 所要封装的javaBean
     * @param rs 记录集
     * @return ArrayList 数组里边装有 多个javaBean
     * @throws Exception
     * @说明：利用反射机制从ResultSet自动绑定到JavaBean；根据记录集自动调用javaBean里边的对应方法。如果javaBean与数据库字段类型不匹配，按String类型封装
     */
    public List<T> getList(Class<T> clazz, ResultSet rs)  {

        Field field = null;
        List<T> lists = new ArrayList<T>();

        // 取得类里边的所有方法
        try {

            // 取得ResultSet列名
            ResultSetMetaData rsmd = rs.getMetaData();
            // 获取记录集中的列数
            int counts = rsmd.getColumnCount();
            // 定义counts个String 变量
            String[] columnNames = new String[counts];
            // 给每个变量赋值
            for (int i = 0; i < counts; i++) {
                columnNames[i] = rsmd.getColumnLabel(i + 1);
            }

            // 变量ResultSet
            while (rs.next()) {
                T t = clazz.newInstance();
                // 反射, 从ResultSet绑定到JavaBean
                for (int i = 0; i < counts; i++) {
                    // 根据 rs 列名 ，组装javaBean里边的其中一个set方法，object 就是数据库第一行第一列的数据了

                    Object value = rs.getObject(columnNames[i]);

                    //这里是获取数据库字段的类型
                    Class<?> dbType = value.getClass();

                    //设置参数类型，此类型应该跟javaBean 里边的类型一样，而不是取数据库里边的类型

                    field = clazz.getDeclaredField(columnNames[i]);
                    Class<?> beanType = field.getType();


                    //如果发生从数据库获取到得类型跟javaBean类型不同，按String类型取吧

                    if(beanType!=dbType){
                        value = rs.getString(columnNames[i]);
                    }
                    String setMethodName = "set" + firstUpperCase(columnNames[i]);
                    // 第一个参数是传进去的方法名称，第二个参数是 传进去的类型；
                    Method m = t.getClass().getMethod(setMethodName,beanType);
                    // 第二个参数是传给set方法数据；如果是get方法可以不写
                    m.invoke(t, value);
                }
                lists.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lists;
    }

    //首写字母变大写
    public static String firstUpperCase(String old){

        return old.substring(0, 1).toUpperCase()+old.substring(1);
    }
}
