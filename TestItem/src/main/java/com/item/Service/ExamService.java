package com.item.Service;

import com.item.Dao.Impl.ExamServiceImpl;
import com.item.pojo.Exams;
import com.item.pojo.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {
    @Autowired
    private ExamServiceImpl examService;
    public List<Exams> findTest(Integer student_id)
    {
        return examService.findTest(student_id);
    }
    public List<Questions> findPage(Integer student_id, String name)
    {
        return examService.findPage(student_id,name);
    }
    public void changeState(String state ,Integer student_id,String name)
    {
        examService.changeState(state,student_id,name);
    }
}
