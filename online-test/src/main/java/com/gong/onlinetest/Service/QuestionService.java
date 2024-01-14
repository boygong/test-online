package com.gong.onlinetest.Service;

import com.gong.onlinetest.DTO.PageBean;
import com.gong.onlinetest.Pojo.Question;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */
public interface QuestionService {
    PageBean GetQuestionByPage(Integer page,Integer pageSize);

    boolean QuestionInsert(Question question);

    boolean ModifyQuestion(Question question);

    void delete(Integer questionId);
}
