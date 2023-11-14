package com.item.Dao;

import com.item.pojo.Exams;
import com.item.pojo.Questions;

import java.util.List;

public interface ExamService {
    public List<Exams> findTest(Integer student_id);
    public List<Questions> findPage(Integer student_id, String name);
    public void changeState(String state ,Integer student_id,String name);
}
