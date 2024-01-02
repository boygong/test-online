package com.gong.onlinetest.Controller;


import com.gong.onlinetest.Pojo.Core;
import com.gong.onlinetest.Service2.CoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "分数相关接口")
public class CoreController {
    @Autowired
    private CoreService coreService;
    @GetMapping("/findAllCore")
    @Operation(summary = "获取所有的分数")
    public Core getAllCore(@RequestParam("student_id") Integer student_id) {
        return coreService.findAllCore(student_id);
    }
}
