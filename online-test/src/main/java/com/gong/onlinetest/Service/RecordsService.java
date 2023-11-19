package com.gong.onlinetest.Service;



import com.gong.onlinetest.Pojo.Records;
import com.gong.onlinetest.Pojo.Student;

import java.util.List;

public interface RecordsService {
    public List<Records> testRecords(Integer student_id);
    public void addRecords(Records records);
    public Student selectAllData(Integer student_id);
}
