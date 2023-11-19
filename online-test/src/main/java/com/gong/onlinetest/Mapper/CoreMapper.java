package com.gong.onlinetest.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CoreMapper {
    @Select("select count(*) from onlinetest.records_test where core > 90 and student_id = #{student_id}")
    public Integer findBestCore(Integer student_id);
    @Select("select count(*) from onlinetest.records_test where core > 60 and core < 90 and student_id = #{student_id}")
    public Integer findMiddleCore(Integer student_id);
    @Select("select count(*) from onlinetest.records_test where core < 60 and student_id = #{student_id}")
    public Integer findBadCore(Integer student_id);
    @Select("select avg(core) from onlinetest.records_test where  student_id = #{student_id}")
    public Double findAvgCore(Integer student_id);
    @Select("select count(*) from onlinetest.records_test where student_id = #{student_id}")
    public Integer findAllNum(Integer student_id);
    @Select("select max(core) from onlinetest.records_test where  student_id = #{student_id}")
    public Integer findMaxCore(Integer student_id);
    @Select("select min(core) from onlinetest.records_test where  student_id = #{student_id}")
    public Integer findMinCore(Integer student_id);
}
