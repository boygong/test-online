package com.gong.onlinetest.Controller;


import com.gong.onlinetest.DTO.PageBean;
import com.gong.onlinetest.Pojo.Records;
import com.gong.onlinetest.Pojo.Student;
import com.gong.onlinetest.Service2.RecordsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name="考试记录相关接口")
public class RecordsController {

    @Autowired
    private RecordsService recordsService;
    @GetMapping("/records")
    @Operation(summary = "分页查询考试记录")
    public PageBean getPageList(@RequestParam("student_id") Integer student_id
            , @RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "10") Integer pageSize) {
        PageBean pageBean = recordsService.testRecords(student_id,page,pageSize);
        return pageBean;
    }

    @PostMapping("/addRecords")
    @Operation(summary = "添加考试记录")
    public void addRecordList(@RequestBody Records records)
    {
        recordsService.addRecords(records);
    }
    @GetMapping("/findAllData")
    @Operation(summary = "查询学生所有考试记录")
    public Student getAllData(@RequestParam("studentId") Integer studentId) {
        return recordsService.selectAllData(studentId);
    }
}
