package com.gong.onlinetest.Controller;

import com.github.pagehelper.Page;
import com.gong.onlinetest.DTO.PageBean;
import com.gong.onlinetest.Pojo.Question;
import com.gong.onlinetest.Pojo.Result;
import com.gong.onlinetest.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/getQuestion")
    public Result getQuestion(
            @RequestParam(defaultValue = "1") int currentPage,
            @RequestParam(defaultValue = "10") int pageSize) {

        PageBean pageBean = questionService.GetQuestionByPage(currentPage, pageSize);
        return Result.success(pageBean);
    }

    @PostMapping("/questionInsert")
    public Result QuestionInsert(@RequestBody Question question){
        boolean flag = questionService.QuestionInsert(question);
        if (flag){
            return Result.success();
        }
        return Result.error("插入失败");
    }


    @PutMapping("/modifyQuestion")
    public Result ModifyQuestion(@RequestBody Question question){
        boolean flag = questionService.ModifyQuestion(question);
        if (flag){
            return Result.success();
        }
        else {
            return Result.error("信息修改失败");
        }

    }
}
