package com.gong.onlinetest.Pojo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */
@Data
public class StudentExam {
    private Integer studentId;
    private String state;
    private String name;
    private LocalDateTime time;
    private String core;
}
