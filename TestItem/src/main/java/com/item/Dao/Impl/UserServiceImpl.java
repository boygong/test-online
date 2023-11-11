package com.item.Dao.Impl;

import com.item.Dao.UserService;
import com.item.Mapper.UserMapper;
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
    public Student selectAllData(Integer student_id)
    {
        return userMapper.selectAllData(student_id);
    }
    public Core selectAllCore(Integer student_id)
    {
        Core core = new Core();
        core.setMaxCore(userMapper.findMaxCore(student_id));
        core.setMinCore(userMapper.findMinCore(student_id));
        core.setTotal(userMapper.findAllNum(student_id));
        core.setBestCore(userMapper.findBestCore(student_id));
        core.setMiddleCore(userMapper.findMiddleCore(student_id));
        core.setBadCore(userMapper.findBadCore(student_id));
        core.setAverageCore(userMapper.findAvgCore(student_id));
        return core;
    }
}
