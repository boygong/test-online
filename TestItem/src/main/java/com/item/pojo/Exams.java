package com.item.pojo;

import lombok.Data;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDateTime;

@Data
public class Exams {
    private Integer id;
    private String name;
    private String teacher_name;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private String state;
}
