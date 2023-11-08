package com.item.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

public class Student {
    private Integer id;
    private String name;
    private String password;
    private Integer class_name;
    private Integer student_id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getClass_name() {
        return class_name;
    }

    public void setClass_name(Integer class_name) {
        this.class_name = class_name;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", class_name=" + class_name +
                ", student_id=" + student_id +
                '}';
    }

    public Student(Integer id, String name, String password, Integer class_name, Integer student_id) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.class_name = class_name;
        this.student_id = student_id;
    }
    public Student(){}
}
