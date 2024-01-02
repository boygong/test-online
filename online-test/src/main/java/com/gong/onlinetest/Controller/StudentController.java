package com.gong.onlinetest.Controller;

import com.gong.onlinetest.DTO.PageBean;
import com.gong.onlinetest.Pojo.Result;
import com.gong.onlinetest.Pojo.Student;
import com.gong.onlinetest.Service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */

@RestController
@RequestMapping("/student")
@Tag(name="学生相关接口")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/getAllStudent")
    @Operation(summary = "分页查询学生")
    public Result GetAllStudent() {
        PageBean pageBean = studentService.getStudentByPage(1, 10);
        return Result.success(pageBean);
    }

    @GetMapping("/getStudentByPage")
    @Operation(summary = "分页查询学生增强版")
    public Result GeStudentByPage(@Param("page") Integer page, @Param(("pageSize")) Integer pageSize) {
        PageBean pageBean = studentService.getStudentByPage(page, pageSize);
        return Result.success(pageBean);
    }

    @GetMapping("/getStudentByInform")
    @Operation(summary = "动态查询学生")
    public Result GetStudentByInform(@RequestParam(name = "studentId", required = false) Integer studentId,
                                     @RequestParam(name = "className", required = false) Integer className,
                                     @RequestParam(name = "name", required = false) String name) { //根据姓名，学号，班级动态查询学生
        int totalPages = studentService.getAllPages();  // 从数据库查询获取总记录数
        List<Student> students = studentService.getStudentByInform(studentId,className,name);
        Map<String, Object> response = new HashMap<>();
        response.put("totalPages", totalPages);
        response.put("students", students);
        return Result.success(response);
    }

    @PutMapping("/modifyStudent")
    @Operation(summary = "修改学生信息")
    public Result ModifyStudent(@RequestBody Student student){
        boolean flag = studentService.modifyStudent(student);
        if (flag){
            return Result.success();
        }
        else {
            return Result.error(student.getName()+"的信息修改失败");
        }
    }
}
