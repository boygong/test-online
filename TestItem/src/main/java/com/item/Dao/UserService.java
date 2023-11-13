package com.item.Dao;

import com.item.pojo.*;

import java.util.List;

public interface UserService {
    public String select_password(Integer student_id);
    public void addUser(Student student);

}
