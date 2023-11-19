package com.gong.onlinetest.Service2;

import com.gong.onlinetest.Service.impl.UserServiceImpl;
import com.gong.onlinetest.Pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserServiceImpl userService;
    public String selectById(Integer student_id)
    {
        return userService.select_password(student_id);
    }
    public void addUser(Student student){
        userService.addUser(student);
    }
}
