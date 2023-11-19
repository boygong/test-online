package com.gong.onlinetest.Service;

import com.gong.onlinetest.DTO.TeacherExam;
import com.gong.onlinetest.DTO.TeacherExamPlus;
import com.gong.onlinetest.Pojo.Exam;
import com.gong.onlinetest.Pojo.Question;
import com.gong.onlinetest.Pojo.Questions;
import com.gong.onlinetest.Pojo.StudentExam;

import java.util.List;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */
public interface ExamService {
    List<TeacherExam> GetTeacherExam();

    boolean InsertExam(TeacherExamPlus teacherExamPlus);

    public List<Exam> findTest(Integer student_id);
    public List<Questions> findPage(Integer student_id, String name);
    public void changeState(String state ,Integer student_id,String name);

    List<StudentExam> GetExamById(Integer examId);
}
