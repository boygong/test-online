package com.item.Dao.Impl;

import com.item.Mapper.RecordsMapper.RecordsMapper;
import com.item.Dao.RecordsService;
import com.item.pojo.Records;
import com.item.pojo.Student;
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
