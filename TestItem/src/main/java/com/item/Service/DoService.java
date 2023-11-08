package com.item.Service;

import com.item.Dao.Impl.UserServiceImpl;
import com.item.pojo.Exams;
import com.item.pojo.Questions;
import com.item.pojo.Records;
import com.item.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DoService {
    @Autowired
    private UserServiceImpl userService;
    public String selectById(Integer student_id)
    {
        return userService.select_password(student_id);
    }
    public void addUser(Student student){
        userService.addUser(student);
    }
    public List<Exams> findTest(Integer student_id)
    {
        return userService.findTest(student_id);
    }
    public List<Questions> findPage(Integer student_id, String name)
    {
        return userService.findPage(student_id,name);
    }
    public List<Records> testRecords(Integer student_id){
        return userService.testRecords(student_id);
    }
    public void addRecords(Records records)
    {
        userService.addRecords(records);
    }
    public void changeState(String state ,Integer student_id,String name)
    {
        userService.changeState(state,student_id,name);
    }
}
