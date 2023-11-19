package com.gong.onlinetest.DTO;

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
public class TeacherExam {
    private Integer id;
    private Integer teacherId;
    private String teacherName;
    private String examName;
    private LocalDateTime endTime;
    private LocalDateTime startTime;
    private Integer className;
}
