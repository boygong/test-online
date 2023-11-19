package com.gong.onlinetest.Mapper;

import com.gong.onlinetest.Pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */
@Mapper
public interface StudentMapper {
    @Select("select * from students")
    List<Student> SelectAllStudents();

    @Select("select student_id from students where class_name=#{className}")
    List<Integer> SelectStudentByClass(Integer className);

    @Select("select * from students limit #{begin},#{end}")
    List<Student> SelectStudentsByPage(int begin, int end);


    @Select("select count(*) from students")
    Integer SelectAllPages();


    List<Student> SelectStudent(Integer studentId,Integer className,String name);

    @Update("update students set name=#{name},password=#{password},class_name=#{className},student_id=#{studentId} where id=#{id}")
    int updateStudent(Student student);
}
