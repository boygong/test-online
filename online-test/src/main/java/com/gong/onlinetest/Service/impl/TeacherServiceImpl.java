package com.gong.onlinetest.Service.impl;

import com.gong.onlinetest.Mapper.QuestionMapper;
import com.gong.onlinetest.Mapper.TeacherMapper;
import com.gong.onlinetest.Pojo.Question;
import com.gong.onlinetest.Pojo.Teacher;
import com.gong.onlinetest.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public Teacher TeacherLogin(Teacher teacher) {
        Teacher teacherById = teacherMapper.getTeacherById(teacher);
        return teacherById;
    }


}
