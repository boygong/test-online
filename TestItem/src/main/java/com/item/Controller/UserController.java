package com.item.Controller;

import com.item.Service.DoService;
import com.item.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private DoService doService;

    @PostMapping("/admin")
    public ResponseEntity<String> identification(@RequestBody Student student) {
        String password = doService.selectById(student.getStudent_id());
        if (password != null && password.equals(student.getPassword())) {
            return ResponseEntity.ok("登录成功");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("密码错误");
    }
    @PostMapping("/sign")
    public ResponseEntity<String> register(@RequestBody Student registration) {
        doService.addUser(registration);
        if (doService.selectById(registration.getStudent_id() ) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("请填写完整信息");
        }
        return ResponseEntity.ok("注册成功");
    }

    @GetMapping("/find")
    public List<Exams> getList(@RequestParam("student_id") Integer student_id) {
        return doService.findTest(student_id);
    }
    @GetMapping("/testPage")
    public List<Questions> getPageList(@RequestParam("student_id") Integer student_id,@RequestParam("name")String name) {
        return doService.findPage(student_id,name);
    }
    @GetMapping("/records")
    public PageBean getPageList(@RequestParam("student_id") Integer student_id
    ,@RequestParam(defaultValue = "1") Integer page,
     @RequestParam(defaultValue = "10") Integer pageSize) {
        PageBean pageBean = doService.testRecords(student_id,page,pageSize);
        return pageBean;
    }

    @PostMapping("/addRecords")
    public void addRecordList(@RequestBody Records records)
    {
        doService.addRecords(records);
    }

    @PutMapping("/update")
    public void changeExamState(@RequestParam("state") String state ,@RequestParam("student_id") Integer student_id,@RequestParam("name") String name)
    {
        doService.changeState(state,student_id,name);
    }
}
