package com.gong.onlinetest.Service.impl;

import com.gong.onlinetest.DTO.TeacherExam;
import com.gong.onlinetest.DTO.TeacherExamPlus;
import com.gong.onlinetest.Mapper.ExamMapper;
import com.gong.onlinetest.Mapper.StudentMapper;
import com.gong.onlinetest.Pojo.Exam;
import com.gong.onlinetest.Pojo.Question;
import com.gong.onlinetest.Pojo.Questions;
import com.gong.onlinetest.Pojo.StudentExam;
import com.gong.onlinetest.Service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */
@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<TeacherExam> GetTeacherExam() {
        return examMapper.SelectTeacherExam();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean InsertExam(TeacherExamPlus teacherExamPlus) {
        try {
            int row = examMapper.InsertExam(teacherExamPlus);
            List<Question> questions = teacherExamPlus.getQuestions();
            Integer examId = teacherExamPlus.getId();
            if (row == 1) {
                questions.forEach(question -> {
                    Integer questionId = question.getId();
                    int row2 = examMapper.InsertTeacherExam(questionId, examId);
                    if (row2 == 0) {
                        throw new RuntimeException("插入考试中的问题失败");
                    }
                });
                List<Integer> studentIds = studentMapper.SelectStudentByClass(teacherExamPlus.getClassName());
                studentIds.forEach(studentId->{
                    int i = examMapper.InsertStudentExam(studentId, examId);
                    if (i==0){
                        throw new RuntimeException("插入学生考试中的问题失败");
                    }
                });
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            throw e; // 继续抛出异常以确保事务回滚
        }
    }

    @Override
    public List<Exam> findTest(Integer student_id) {
        return examMapper.findTest(student_id);
    }

    @Override
    public List<Questions> findPage(Integer student_id, String name) {
        return examMapper.findPage(student_id,name);
    }

    @Override
    public void changeState(String state, Integer student_id, String name) {
        examMapper.changeState(state,student_id,name);
    }

    @Override
    public List<StudentExam> GetExamById(Integer examId) {
        List<StudentExam> studentExams = examMapper.SelectExamById(examId);
        return studentExams;
    }


}
