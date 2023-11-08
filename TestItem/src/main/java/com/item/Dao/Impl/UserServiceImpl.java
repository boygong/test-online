package com.item.Dao.Impl;

import com.item.Dao.UserService;
import com.item.Mapper.UserMapper;
import com.item.pojo.Exams;
import com.item.pojo.Questions;
import com.item.pojo.Records;
import com.item.pojo.Student;
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

    @Override
    public List<Exams> findTest(Integer student_id) {
        return userMapper.findTest(student_id);
    }

    @Override
    public List<Questions> findPage(Integer student_id, String name) {
        return userMapper.findPage(student_id,name);
    }
    public List<Records> testRecords(Integer student_id){
        return userMapper.testRecords(student_id);
    }

    @Override
    public void addRecords(Records records) {
        userMapper.addRecords(records);
    }

    @Override
    public void changeState(String state, Integer student_id, String name) {
        userMapper.changeState(state,student_id,name);
    }

}
