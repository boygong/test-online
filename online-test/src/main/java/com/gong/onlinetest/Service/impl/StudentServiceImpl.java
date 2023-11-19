package com.gong.onlinetest.Service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gong.onlinetest.DTO.PageBean;
import com.gong.onlinetest.Mapper.StudentMapper;
import com.gong.onlinetest.Pojo.Student;
import com.gong.onlinetest.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public List<Student> GetAllStudent() {
        return studentMapper.SelectAllStudents();
    }

    @Override
    public PageBean getStudentByPage(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<Student> students = studentMapper.SelectAllStudents();

        Page<Student> studentPage = (Page<Student>) students;

        PageBean pageBean = new PageBean(studentPage.getTotal(), studentPage.getResult());
        return pageBean;
    }

    @Override
    public int getAllPages() {
        return studentMapper.SelectAllPages();
    }

    @Override
    public List<Student> getStudentByInform(Integer studentId, Integer className, String name) {
        return studentMapper.SelectStudent(studentId,className,name);
    }

    @Override
    public boolean modifyStudent(Student student) {
        int row = studentMapper.updateStudent(student);

        return row==1? true:false;
    }
}
