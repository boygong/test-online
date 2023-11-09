package com.item.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class Student {
    private Integer id;
    private String name;
    private String password;
    private Integer class_name;
    private Integer student_id;
    private String image;

}
