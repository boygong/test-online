package com.gong.onlinetest.Mapper;

import com.gong.onlinetest.Pojo.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */


@Mapper
public interface TeacherMapper {

    @Select("select * from onlinetest.teacher where teacher_id=#{teacherId}")
    public Teacher getTeacherById(Teacher teacher);
}
