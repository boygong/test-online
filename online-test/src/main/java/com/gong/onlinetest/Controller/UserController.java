package com.gong.onlinetest.Controller;

import com.gong.onlinetest.Pojo.Student;
import com.gong.onlinetest.Service2.UserService;
import com.gong.onlinetest.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/admin")
    public ResponseEntity<String> identification(@RequestBody Student student) {
        String password = userService.selectById(student.getStudentId());
        if (password != null && password.equals(student.getPassword())) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",student.getStudentId());
             String jwt = JwtUtils.generateJwt(claims);
            System.out.println(jwt);
            return ResponseEntity.ok(jwt);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("密码错误");
    }
    @PostMapping("/sign")
    public ResponseEntity<String> register(@RequestBody Student registration) {
        userService.addUser(registration);
        if (userService.selectById(registration.getStudentId() ) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("请填写完整信息");
        }
        return ResponseEntity.ok("注册成功");
    }
}
