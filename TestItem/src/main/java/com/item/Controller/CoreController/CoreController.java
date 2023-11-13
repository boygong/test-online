package com.item.Controller.CoreController;

import com.item.Service.CoreService;
import com.item.pojo.Core;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoreController {
    @Autowired
    private CoreService coreService;
    @GetMapping("/findAllCore")
    public Core getAllCore(@RequestParam("student_id") Integer student_id) {
        return coreService.findAllCore(student_id);
    }
}
