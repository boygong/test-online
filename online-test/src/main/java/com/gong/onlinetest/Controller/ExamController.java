package com.gong.onlinetest.Controller;

import com.gong.onlinetest.DTO.TeacherExam;
import com.gong.onlinetest.DTO.TeacherExamPlus;
import com.gong.onlinetest.Pojo.*;
import com.gong.onlinetest.Service.ExamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */
@RestController
@RequestMapping("/exam")
@Tag(name = "考试相关接口")
public class ExamController {
    @Autowired
    private ExamService examService;


    @GetMapping("/getTeacherExam")
    @Operation(summary = "获取老师与考试关系表")
    public Result GetTeacherExam() {
        List<TeacherExam> teacherExams = examService.GetTeacherExam();
        return Result.success(teacherExams);
    }


    @PostMapping("/insertExam")
    @Operation(summary = "插入考试")
    public Result InsertExam(@RequestBody TeacherExamPlus teacherExamPlus) {
        System.out.println(teacherExamPlus.getTeacherId());
        boolean flag = examService.InsertExam(teacherExamPlus);
        if (flag) {
            return Result.success();
        }else {
            return Result.error("插入失败");
        }
    }

    @GetMapping("getExamById")
    @Operation(summary = "根据id查询考试")
    public Result GetExamById(@RequestParam Integer examId){
        List<StudentExam> studentExams = examService.GetExamById(examId);
        return Result.success(studentExams);
    }

    @GetMapping("getExamByStudent")
    @Operation(summary = "根据学生查询考试")
    public Result GetExamByStu(@RequestParam Integer studentId){
        List<StudentExam> studentExams = examService.GetExamByStu(studentId);
        return Result.success(studentExams);
    }
}
