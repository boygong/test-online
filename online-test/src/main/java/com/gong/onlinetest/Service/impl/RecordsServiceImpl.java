package com.gong.onlinetest.Service.impl;

import com.gong.onlinetest.Mapper.RecordsMapper;
import com.gong.onlinetest.Pojo.Records;
import com.gong.onlinetest.Pojo.Student;
import com.gong.onlinetest.Service.RecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecordsServiceImpl implements RecordsService {
    @Autowired
    private RecordsMapper recordsMapper;

    public List<Records> testRecords(Integer student_id){
        return recordsMapper.testRecords(student_id);
    }

    public void addRecords(Records records) {
        recordsMapper.addRecords(records);
    }

    public Student selectAllData(Integer student_id)
    {
        return recordsMapper.selectAllData(student_id);
    }
}
