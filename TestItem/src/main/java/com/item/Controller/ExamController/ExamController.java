package com.item.Controller.ExamController;

import com.item.Service.ExamService;
import com.item.pojo.Exams;
import com.item.pojo.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExamController {
    @Autowired
    private ExamService examService;
    @GetMapping("/find")
    public List<Exams> getList(@RequestParam("student_id") Integer student_id) {
        return examService.findTest(student_id);
    }
    @GetMapping("/testPage")
    public List<Questions> getPageList(@RequestParam("student_id") Integer student_id, @RequestParam("name")String name) {
        return examService.findPage(student_id,name);
    }

    @PutMapping("/update")
    public void changeExamState(@RequestParam("state") String state ,@RequestParam("student_id") Integer student_id,@RequestParam("name") String name)
    {
        examService.changeState(state,student_id,name);
    }
}
