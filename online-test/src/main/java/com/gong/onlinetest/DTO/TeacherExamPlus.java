package com.gong.onlinetest.DTO;

import com.gong.onlinetest.Pojo.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherExamPlus {
    private Integer id;
    private Integer teacherId;
    private String teacherName;
    private String examName;
    private LocalDateTime endTime;
    private LocalDateTime startTime;
    private List<Question> questions;
    private Integer className;
}
