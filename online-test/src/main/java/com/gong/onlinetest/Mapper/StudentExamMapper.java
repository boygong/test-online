package com.gong.onlinetest.Mapper;

import com.gong.onlinetest.Pojo.StudentExam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */
@Mapper
public interface StudentExamMapper {
    //根据考试id获取学生考试信息
    @Select("select * from student_exam where exam_id = #{examId}")
    List<StudentExam> getByExamId(Integer examId);

    //修改信息
    void update(StudentExam sExam);
}
