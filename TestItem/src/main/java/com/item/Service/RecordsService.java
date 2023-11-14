package com.item.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.item.Dao.Impl.RecordsServiceImpl;
import com.item.pojo.PageBean;
import com.item.pojo.Records;
import com.item.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordsService {
    @Autowired
    private RecordsServiceImpl recordsService;
    public PageBean testRecords(Integer student_id, Integer page, Integer pageSize){
        PageHelper.startPage(page,pageSize);
        List<Records> recordsList = recordsService.testRecords(student_id);
        Page<Records> recordsPageBean = (Page<Records>) recordsList;
        PageBean pageBean = new PageBean(recordsPageBean.getTotal(), recordsPageBean.getResult());
        return pageBean;
    }
    public void addRecords(Records records)
    {
        recordsService.addRecords(records);
    }
    public Student selectAllData(Integer student_id){
        return recordsService.selectAllData(student_id);
    }
}
