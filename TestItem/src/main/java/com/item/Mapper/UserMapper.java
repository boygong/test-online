package com.item.Mapper;

import com.item.pojo.Exams;
import com.item.pojo.Questions;
import com.item.pojo.Records;
import com.item.pojo.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from onlinetest.students")
    public List<Student> selectAll();

    @Select("select password from onlinetest.students where student_id = #{student_id} ")
    public String selectPassword(Integer student_id);

    @Insert("insert into onlinetest.students(name, password, student_id,class_name) values (#{name},#{password},#{student_id},#{class_name})")
    public void addUser(Student student);

    @Select("select exams.id,state,exams.name,teacher_name,start_time,end_time from onlinetest.student_exam,onlinetest.exams,onlinetest.teacher where student_exam.exam_id = exams.id and student_exam.student_id = #{student_id} and exams.teacher_id = teacher.teacher_id")
    public List<Exams> findTest(Integer student_id);
    @Select("select onlinetest.questions.content,onlinetest.questions.type,onlinetest.questions.opa," +
            "onlinetest.questions.opb,onlinetest.questions.opc,onlinetest.questions.opd,onlinetest.questions.correctAnswer " +
            "from onlinetest.questions,onlinetest.question_exams,onlinetest.student_exam,onlinetest.exams " +
            "where student_exam.exam_id= question_exams.exam_id and question_exams.question_id = questions.id and student_exam.student_id =" +
            "#{student_id} and exams.name = #{name} and exams.id = student_exam.exam_id")
    public List<Questions> findPage(Integer student_id, String name);
    @Select("select * from onlinetest.records_test " +
            "where onlinetest.records_test.student_id = #{student_id}")
    public List<Records> testRecords(Integer student_id);
    @Insert("insert into onlinetest.records_test(name, time, core, student_id) VALUES " +
            "(#{name},#{time},#{core},#{student_id})")
    public void addRecords(Records records);
    @Update("update onlinetest.student_exam set state = #{state} where student_id = #{student_id} and name = #{name}")
    public void changeState(String state ,Integer student_id,String name);
    @Select("select * from onlinetest.students where student_id = #{student_id} ")
    public Student selectAllData(Integer student_id);
}
