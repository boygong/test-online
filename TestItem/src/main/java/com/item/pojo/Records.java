package com.item.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Records {
    private Integer student_id;
    private String name;
    private LocalDateTime time;
    private Integer core;

}
