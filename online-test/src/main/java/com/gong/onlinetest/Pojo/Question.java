package com.gong.onlinetest.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */

@Data
@AllArgsConstructor
public class Question {
    private Integer id;
    private String content;
    private Integer type;
    private String opa;
    private String opb;
    private String opc;
    private String opd;
    private String currentAnswer;
}
