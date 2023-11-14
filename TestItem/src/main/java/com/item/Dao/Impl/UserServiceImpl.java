package com.item.Dao.Impl;

import com.item.Dao.UserService;
import com.item.Mapper.UserMapper.UserMapper;
import com.item.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public String select_password(Integer student_id) {
        return userMapper.selectPassword(student_id);
    }

    @Override
    public void addUser(Student student) {
        userMapper.addUser(student);
    }

}
