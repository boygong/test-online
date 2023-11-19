package com.gong.onlinetest.Controller;

import com.gong.onlinetest.DTO.PageBean;
import com.gong.onlinetest.Pojo.Result;
import com.gong.onlinetest.Pojo.Student;
import com.gong.onlinetest.Service.StudentService;
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
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/getAllStudent")
    public Result GetAllStudent() {
        PageBean pageBean = studentService.getStudentByPage(1, 10);
        return Result.success(pageBean);
    }

    @GetMapping("/getStudentByPage")
    public Result GeStudentByPage(@Param("page") Integer page, @Param(("pageSize")) Integer pageSize) {
        PageBean pageBean = studentService.getStudentByPage(page, pageSize);
        return Result.success(pageBean);
    }

    @GetMapping("/getStudentByInform")
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
