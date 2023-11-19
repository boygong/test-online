package com.gong.onlinetest.Mapper;

import com.gong.onlinetest.Pojo.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from onlinetest.students")
    public List<Student> selectAll();

    @Select("select password from onlinetest.students where student_id = #{student_id} ")
    public String selectPassword(Integer student_id);
    @Insert("insert into onlinetest.students(name, password, student_id,class_name) values (#{name},#{password},#{student_id},#{class_name})")
    public void addUser(Student student);
}
