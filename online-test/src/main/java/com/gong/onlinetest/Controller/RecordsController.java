package com.gong.onlinetest.Controller;


import com.gong.onlinetest.DTO.PageBean;
import com.gong.onlinetest.Pojo.Records;
import com.gong.onlinetest.Pojo.Student;
import com.gong.onlinetest.Service2.RecordsService;
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
    public Student getAllData(@RequestParam("studentId") Integer studentId) {
        return recordsService.selectAllData(studentId);
    }
}
