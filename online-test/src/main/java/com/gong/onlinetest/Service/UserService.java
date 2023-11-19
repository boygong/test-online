package com.gong.onlinetest.Service;

import com.gong.onlinetest.Pojo.Student;

public interface UserService {
    public String select_password(Integer student_id);
    public void addUser(Student student);

}
