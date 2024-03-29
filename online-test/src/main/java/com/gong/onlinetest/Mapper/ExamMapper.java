package com.gong.onlinetest.Mapper;

import com.gong.onlinetest.DTO.TeacherExam;
import com.gong.onlinetest.DTO.TeacherExamPlus;
import com.gong.onlinetest.Pojo.*;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */
@Mapper
public interface ExamMapper {

    @Select("select teacher.name as teacherName,exams.id, exams.teacher_id, exams.name as examName,  end_time, start_time ,className from teacher , exams " +
            "where exams.teacher_id=teacher.teacher_id")
    public List<TeacherExam> SelectTeacherExam();

    @Insert("insert into exams set name=#{examName},className=#{className},start_time=#{startTime},end_time=#{endTime},teacher_id=#{teacherId}")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int InsertExam(TeacherExamPlus teacherExamPlus);


    @Insert("insert into question_exams set question_id=#{questionId},exam_id=#{examId}")
    int InsertTeacherExam(Integer questionId, Integer examId);

    @Insert("INSERT INTO student_exam (student_id, exam_id, state, name) " +
            "VALUES (#{studentId}, #{examId}, '未开始', (SELECT MAX(name) FROM exams WHERE exams.id = #{examId}))")
    int InsertStudentExam(Integer studentId, Integer examId);


    @Select("select exams.id,state,exams.name,teacher.name as teacherName ,start_time as startTime,end_time as endTime " +
            "from onlinetest.student_exam,onlinetest.exams,onlinetest.teacher " +
            "where student_exam.exam_id = exams.id and student_exam.student_id = #{student_id} and exams.teacher_id = teacher.teacher_id")
    public List<Exam> findTest(Integer student_id);

    @Select("select onlinetest.questions.content,onlinetest.questions.type,onlinetest.questions.opa," +
            "onlinetest.questions.opb,onlinetest.questions.opc,onlinetest.questions.opd,onlinetest.questions.correntAnswer as correctAnswer " +
            "from onlinetest.questions,onlinetest.question_exams,onlinetest.student_exam,onlinetest.exams " +
            "where student_exam.exam_id= question_exams.exam_id and question_exams.question_id = questions.id and student_exam.student_id =" +
            "#{student_id} and exams.name = #{name} and exams.id = student_exam.exam_id")
    public List<Questions> findPage(Integer student_id, String name);

    @Update("update onlinetest.student_exam set state = #{state} where student_id = #{student_id} and name = #{name}")
    public void changeState(String state, Integer student_id, String name);

    @Select("SELECT student_exam.student_id, students.name as studentName, student_exam.state, student_exam.name as examName, time, core " +
            "FROM students," +
            "     student_exam" +
            "         LEFT JOIN records_test" +
            "                   ON student_exam.student_id = records_test.student_id and student_exam.name = records_test.name " +
            "where exam_id = #{examId}" +
            "  and students.student_id = student_exam.student_id;")
    List<StudentExam> SelectExamById(Integer examId);

    @Select("SELECT student_exam.student_id, students.name as studentName, student_exam.state, student_exam.name AS exam_name, time, core " +
            "FROM students" +
            "         JOIN student_exam ON students.student_id = student_exam.student_id" +
            "         LEFT JOIN records_test" +
            "                   ON student_exam.student_id = records_test.student_id AND student_exam.name = records_test.name " +
            "WHERE student_exam.student_id = #{studentId};")
    List<StudentExam> SelectExamByStu(Integer studentId);

    //查询考试过期的考试id
    @Select("select id from exams where end_time<#{now}")
    List<Integer> getByExpireTime(LocalDateTime now);

    //考试明细
    @Select("select id, name, teacher_id, end_time, start_time, className, id, name, time, core, student_id" +
            " from exams left join records_test join student_exam se on exams.id = se.exam_id and exam_id=#{examId}")
    List<Records> getRecordByEXamId(Integer examId);
}
