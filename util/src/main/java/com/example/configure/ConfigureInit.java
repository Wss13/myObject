package com.example.configure;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.configure.dto.Configuer;
import com.example.servicetest.BeanWayService;
import com.example.util.moudl.AppModule;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/10/31
 */
@Configuration

@ComponentScan("com.example")
public class ConfigureInit {
    String mysqlJDBC = "jdbc:mysql://localhost:3306/demo?user=root&password=123456&useUnicode=true&characterEncoding=utf8&characterSetResults=utf8";
    private static PreparedStatement preparedStatement = null;
    @Bean
    public List test() throws Exception {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(mysqlJDBC);
            ResultSet rs = query(connection, "select t.moudle_name as 'moudleName'," +
                    "t.moudle_key_name as 'moudleKeyName',t.moudle_value as 'moudleValue',t.type as 'type'  from configure t");
            ReflectHelper<Configuer> reflectHelper = new ReflectHelper<Configuer>();
            List<Configuer> configuers = reflectHelper.getList(Configuer.class,rs);
        try{
            for (Configuer configuer:configuers) {
                modify(configuer);
            }
        }catch (Exception e){
        }
        return reflectHelper.getList(Configuer.class,rs);
    }
    @Bean
    public int getMoudl(){
        Set<Class<?>> classes = new Reflections("com.*").getTypesAnnotatedWith(AppModule.class);
        for(Class clazz :classes){
            int a = 1;
        }
        return 1;
    }
    @Bean(initMethod="init",destroyMethod="destroy")
    BeanWayService beanWayService(){
        BeanWayService b = new BeanWayService();
        b.setP(111);
        return b;
    }


    public static ResultSet query(Connection connection, String sql) throws SQLException {
        ResultSet rs = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            return rs;
        } catch (SQLException var4) {
            throw new SQLException(var4);
        }
    }
    private static List convertList(ResultSet rs) throws SQLException{
        List list = new ArrayList();
        ResultSetMetaData md = rs.getMetaData();//获取键名
        int columnCount = md.getColumnCount();//获取行的数量
        while (rs.next()) {
            Map rowData = new HashMap();//声明Map
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), rs.getObject(i));//获取键名及值
            }
            list.add(rowData);
        }
        return list;
    }
    /* 修改模块静态变量的值*/
    public static void modify(Configuer configuer) throws Exception{
        String moudleName = configuer.getMoudleName();
        String newFieldValue = configuer.getMoudleValue();
        Object object = (Object) Class.forName(moudleName).newInstance();
        Field field = object.getClass().getDeclaredField(configuer.getMoudleKeyName());
        Field[] fields = object.getClass().getDeclaredFields();
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        //Field 的 modifiers 是私有的
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        if(!field.isAccessible()) {
            field.setAccessible(true);
        }
        try {
            field.set(object, getNewFieldValue(field,newFieldValue));
        }catch (Exception e){
            System.out.println(e.getCause());
        }
    }
    private static Object getNewFieldValue(Field field,Object newFieldValue){
        switch (field.getType().toString()){
            case "class [Ljava.lang.String;":
                newFieldValue = JSON.parseArray(newFieldValue.toString());
                JSONArray jsonArray= (JSONArray) newFieldValue;
                newFieldValue = jsonArray.toArray(new String[jsonArray.size()]);
                break;
            case "interface java.util.List":
                newFieldValue = JSON.parseArray(newFieldValue.toString());
                break;
            case "interface java.util.Map":
                newFieldValue = JSON.parseObject(newFieldValue.toString(),Map.class);
                break;
            case "long":
                newFieldValue = Long.parseLong(newFieldValue.toString());
                break;
            case "int":
                newFieldValue = Integer.parseInt(newFieldValue.toString());
                break;
            case "boolean":
                newFieldValue = Boolean.parseBoolean(newFieldValue.toString());
                break;
            case "class java.lang.Integer":
                newFieldValue = Integer.parseInt(newFieldValue.toString());
                break;
            case "class java.lang.Long":
                newFieldValue = Long.parseLong(newFieldValue.toString());
                break;
             default:
                 break;
        }
        return newFieldValue;
    }
}
