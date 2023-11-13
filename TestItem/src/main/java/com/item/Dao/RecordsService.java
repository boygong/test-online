package com.item.Dao;

import com.item.pojo.Records;
import com.item.pojo.Student;

import java.util.List;

public interface RecordsService {
    public List<Records> testRecords(Integer student_id);
    public void addRecords(Records records);
    public Student selectAllData(Integer student_id);
}
