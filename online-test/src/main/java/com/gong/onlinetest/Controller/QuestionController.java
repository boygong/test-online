package com.gong.onlinetest.Controller;

import com.github.pagehelper.Page;
import com.gong.onlinetest.DTO.PageBean;
import com.gong.onlinetest.Pojo.Question;
import com.gong.onlinetest.Pojo.Result;
import com.gong.onlinetest.Service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */
@RestController
@RequestMapping("/question")
@Tag(name = "题目相关接口")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/getQuestion")
    @Operation(summary = "分页查询题目")
    public Result getQuestion(
            @RequestParam(defaultValue = "1") int currentPage,
            @RequestParam(defaultValue = "10") int pageSize) {

        PageBean pageBean = questionService.GetQuestionByPage(currentPage, pageSize);
        return Result.success(pageBean);
    }

    @PostMapping("/questionInsert")
    @Operation(summary = "添加题目")
    public Result QuestionInsert(@RequestBody Question question){
        boolean flag = questionService.QuestionInsert(question);
        if (flag){
            return Result.success();
        }
        return Result.error("插入失败");
    }


    @PutMapping("/modifyQuestion")
    @Operation(summary = "修改题目")
    public Result ModifyQuestion(@RequestBody Question question){
        boolean flag = questionService.ModifyQuestion(question);
        if (flag){
            return Result.success();
        }
        else {
            return Result.error("信息修改失败");
        }

    }
    @DeleteMapping("/deleteQuestion")
    @Operation(summary = "删除题目")
    public Result deleteQuestion(@RequestParam Integer questionId){
        questionService.delete(questionId);
        return Result.success();
    }
}
