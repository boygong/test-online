package com.item.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.item.Dao.Impl.UserServiceImpl;
import com.item.pojo.*;
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
    public PageBean testRecords(Integer student_id, Integer page, Integer pageSize){
        PageHelper.startPage(page,pageSize);
        List<Records> recordsList = userService.testRecords(student_id);
        Page<Records> recordsPageBean = (Page<Records>) recordsList;
        PageBean pageBean = new PageBean(recordsPageBean.getTotal(), recordsPageBean.getResult());
        return pageBean;
    }
    public void addRecords(Records records)
    {
        userService.addRecords(records);
    }
    public void changeState(String state ,Integer student_id,String name)
    {
        userService.changeState(state,student_id,name);
    }
    public Student selectAllData(Integer student_id){
        return userService.selectAllData(student_id);
    }
}
