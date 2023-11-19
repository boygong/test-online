package com.gong.onlinetest.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean {
    private long totalPage;
    private List source;
}
