package com.gong.onlinetest.Service.impl;

import com.gong.onlinetest.Pojo.Student;
import com.gong.onlinetest.Service.UserService;
import com.gong.onlinetest.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
