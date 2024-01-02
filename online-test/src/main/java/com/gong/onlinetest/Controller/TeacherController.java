package com.gong.onlinetest.Controller;

import com.gong.onlinetest.Pojo.Question;
import com.gong.onlinetest.Pojo.Result;
import com.gong.onlinetest.Pojo.Teacher;
import com.gong.onlinetest.Service.TeacherService;
import com.gong.onlinetest.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */

@RestController
@RequestMapping("/teacher")
@Tag(name = "老师相关接口")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @PostMapping("/teacherLogin")
    @Operation(summary = "教师登录")
    public Result teacherLogin(@RequestBody Teacher teacher){
        Teacher teacher1 = teacherService.TeacherLogin(teacher);
        if (teacher1!=null && teacher1.getPassword().equals(teacher.getPassword())){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",teacher.getTeacherId());
            String jwt = JwtUtils.generateJwt(claims);
            System.out.println(jwt);
            return Result.success(jwt);
        }
        return Result.error("登录失败,密码或账户不正确");
    }


}
