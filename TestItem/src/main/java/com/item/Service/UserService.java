package com.item.Service;

import com.item.Dao.Impl.UserServiceImpl;
import com.item.pojo.Student;
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
