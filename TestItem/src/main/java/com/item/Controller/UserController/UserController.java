package com.item.Controller.UserController;

import com.item.Service.UserService;
import com.item.pojo.Student;
import com.item.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/admin")
    public ResponseEntity<String> identification(@RequestBody Student student) {
        String password = userService.selectById(student.getStudent_id());
        if (password != null && password.equals(student.getPassword())) {
//            Map<String, Object> claims = new HashMap<>();
//            claims.put("id",student.getStudent_id());
//             String jwt = JwtUtils.generateJwt(claims);
            return ResponseEntity.ok("ok");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("密码错误");
    }
    @PostMapping("/sign")
    public ResponseEntity<String> register(@RequestBody Student registration) {
        userService.addUser(registration);
        if (userService.selectById(registration.getStudent_id() ) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("请填写完整信息");
        }
        return ResponseEntity.ok("注册成功");
    }
}
