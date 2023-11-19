package com.gong.onlinetest.Pojo;

import lombok.Data;

@Data
public class Questions {
        private String content;
        private Integer type;
        private String opa;
        private String opb;
        private String opc;
        private String opd;
        private String correctAnswer;
}
