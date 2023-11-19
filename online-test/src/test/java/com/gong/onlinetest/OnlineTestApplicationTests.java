package com.gong.onlinetest;

import com.gong.onlinetest.DTO.TeacherExam;
import com.gong.onlinetest.Mapper.ExamMapper;
import com.gong.onlinetest.Mapper.QuestionMapper;
import com.gong.onlinetest.Mapper.StudentMapper;
import com.gong.onlinetest.Mapper.TeacherMapper;
import com.gong.onlinetest.Pojo.Question;
import com.gong.onlinetest.Pojo.Student;
import com.gong.onlinetest.Pojo.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class OnlineTestApplicationTests {
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ExamMapper examMapper;

    @Test
    void testGetTeacherById() {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        teacher.setName("廖震南");
        teacher.setTeacherId(1457);
        teacher.setPassword("456789");
        Teacher teacherById= teacherMapper.getTeacherById(teacher);
        System.out.println(teacherById);
    }

    @Test
    void testInsertQuestion(){
        Question question = new Question(16,"测试题目",0,"选项A","选项B","选项C","选项D","C");
        System.out.println(questionMapper.InsertQuestion(question));
    }

    @Test
    void testSelectStudent(){
        List<Student> students = studentMapper.SelectStudent(null, 1, null);
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    void testSelectTeacher(){
        List<TeacherExam> teacherExams = examMapper.SelectTeacherExam();
        teacherExams.forEach(teacherExam -> {
            System.out.println(teacherExam);
        });
    }
}
