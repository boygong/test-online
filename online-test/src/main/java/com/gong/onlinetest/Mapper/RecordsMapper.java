package com.gong.onlinetest.Mapper;


import com.gong.onlinetest.Pojo.Records;
import com.gong.onlinetest.Pojo.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RecordsMapper {
    @Select("select * from onlinetest.records_test " +
            "where onlinetest.records_test.student_id = #{student_id}")
    public List<Records> testRecords(Integer student_id);
    @Insert("insert into onlinetest.records_test(name, time, core, student_id) VALUES " +
            "(#{name},#{time},#{core},#{student_id})")
    public void addRecords(Records records);
    @Select("select * from onlinetest.students where student_id = #{student_id} ")
    public Student selectAllData(Integer student_id);
}
