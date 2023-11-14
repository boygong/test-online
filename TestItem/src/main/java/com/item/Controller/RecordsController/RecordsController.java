package com.item.Controller.RecordsController;

import com.item.Service.RecordsService;
import com.item.pojo.PageBean;
import com.item.pojo.Records;
import com.item.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecordsController {

    @Autowired
    private RecordsService recordsService;
    @GetMapping("/records")
    public PageBean getPageList(@RequestParam("student_id") Integer student_id
            , @RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "10") Integer pageSize) {
        PageBean pageBean = recordsService.testRecords(student_id,page,pageSize);
        return pageBean;
    }

    @PostMapping("/addRecords")
    public void addRecordList(@RequestBody Records records)
    {
        recordsService.addRecords(records);
    }
    @GetMapping("/findAllData")
    public Student getAllData(@RequestParam("student_id") Integer student_id) {
        return recordsService.selectAllData(student_id);
    }
}
