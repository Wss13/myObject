package com.example.service.configure;

import com.example.dao.UserMapper;
import com.example.dto.User;
import com.example.service.UserSMO;
import javafx.concurrent.Worker;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Demo class
 *
 * @author liumc
 * @date 2018/12/24
 */
@Service(value = "UserSMOImpl")
public class UserSMOImpl implements UserSMO {
    @Autowired
    UserMapper userMapper;
    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public void insertBatchUser() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            userList.add(user);
            user.setId(i + "id");
            user.setPassword(i + "password");
            user.setUsername(i + "username");
            user.setSex(i + "sex");
        }

        userMapper.insertBatchUser(userList);
    }

    @Override
    public void insertBatchUserToSqlSession() {
        SqlSession session = sqlSessionTemplate.getSqlSessionFactory()
                .openSession(ExecutorType.BATCH, false);
        try {
            UserMapper batchUserDao = session.getMapper(UserMapper.class);

            for (int i = 0; i < 10; i++) {
                User user = new User();
                user.setId(i + "0id");
                user.setPassword(i + "0password");
                user.setUsername(i + "0username");
                user.setSex(i + "0sex");
                batchUserDao.insertUser(user);
            }
            session.commit();
            // 清理缓存，防止溢出
            session.clearCache();


        } finally {
            session.close();
        }
    }
}
