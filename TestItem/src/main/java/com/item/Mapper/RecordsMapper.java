package com.item.Mapper;

import com.item.pojo.Records;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface RecordsMapper {
    @Select("select count(*) from onlinetest.records_test")
    public Long count();
    @Select("select * from onlinetest.records_test where onlinetest.records_test.student_id = #{student_id} limit #{start},#{pageSize}")
    public List<Records> page(Integer start , Integer pageSize, Integer student_id);
}
