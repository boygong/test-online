package com.item.Dao;

import com.item.pojo.*;

import java.util.List;

public interface UserService {
    public String select_password(Integer student_id);
    public void addUser(Student student);
    public List<Exams> findTest(Integer student_id);
    public List<Questions> findPage(Integer student_id, String name);
    public List<Records> testRecords(Integer student_id);
    public void addRecords(Records records);
    public void changeState(String state ,Integer student_id,String name);
    public Student selectAllData(Integer student_id);
    public Core selectAllCore(Integer student_id);
}
