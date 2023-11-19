package com.gong.onlinetest.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exam {
    private Integer id;
    private String name;
    private Integer teacherId;
    private LocalDateTime endTime;
    private LocalDateTime startTime;
    private String state;
    private String teacherName;
}
