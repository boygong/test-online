package com.gong.onlinetest.Service;

import com.gong.onlinetest.DTO.PageBean;
import com.gong.onlinetest.Pojo.Student;

import java.util.List;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */
public interface StudentService {
    List<Student> GetAllStudent();

    PageBean getStudentByPage(Integer page, Integer pageSize);

    int getAllPages();

    List<Student> getStudentByInform(Integer studentId, Integer className, String name);

    boolean modifyStudent(Student student);
}
