package com.item.Dao.Impl;

import com.item.Dao.ExamService;
import com.item.Mapper.ExamMapper.ExamMapper;
import com.item.pojo.Exams;
import com.item.pojo.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamMapper examMapper;
    @Override
    public List<Exams> findTest(Integer student_id) {
        return examMapper.findTest(student_id);
    }

    @Override
    public List<Questions> findPage(Integer student_id, String name) {
        return examMapper.findPage(student_id,name);
    }

    @Override
    public void changeState(String state, Integer student_id, String name) {
            examMapper.changeState(state,student_id,name);
    }
}
