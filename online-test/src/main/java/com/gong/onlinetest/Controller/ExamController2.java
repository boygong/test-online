package com.gong.onlinetest.Controller;

import com.gong.onlinetest.Pojo.Exam;
import com.gong.onlinetest.Pojo.Question;
import com.gong.onlinetest.Pojo.Questions;
import com.gong.onlinetest.Service.ExamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */
@Slf4j
@RestController
@Tag(name = "考试相关接口2")
public class ExamController2 {
    @Autowired
    private ExamService examService;
    @GetMapping("/find")
    @Operation(summary = "根据学生id获取考试")
    public List<Exam> getList(@RequestParam("student_id") Integer student_id) {
        return examService.findTest(student_id);
    }
    @GetMapping("/testPage")
    @Operation(summary = "根据学生id分页获取考试")
    public List<Questions> getPageList(@RequestParam("student_id") Integer student_id, @RequestParam("name")String name) {
        return examService.findPage(student_id,name);
    }

    @PutMapping("/update")
    @Operation(summary = "根据学生id更新考试状态")
    public void changeExamState(@RequestParam("state") String state ,@RequestParam("student_id") Integer student_id,@RequestParam("name") String name)
    {
        examService.changeState(state,student_id,name);
    }
}
