package com.gong.onlinetest.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gong.onlinetest.DTO.PageBean;
import com.gong.onlinetest.Mapper.QuestionExamsMapper;
import com.gong.onlinetest.Mapper.QuestionMapper;
import com.gong.onlinetest.Pojo.Question;
import com.gong.onlinetest.Pojo.QuestionExams;
import com.gong.onlinetest.Service.QuestionService;
import com.gong.onlinetest.exception.DeleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExamsMapper questionExamsMapper;

    @Override
    public PageBean GetQuestionByPage(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Question> questions = questionMapper.SelectAllQuestion();
        Page<Question> questionPage = (Page<Question>) questions;

        PageBean pageBean = new PageBean(questionPage.getTotal(), questionPage.getResult());
        return pageBean;
    }

    @Override
    public boolean QuestionInsert(Question question) {
        int i = questionMapper.InsertQuestion(question);
        if (i==1){
            return true;
        }
        return false;
    }

    @Override
    public boolean ModifyQuestion(Question question) {
        if (question.getType()==1){ //如果为选择题，则将C、D选项进行清理为null值
            question.setOpc(null);
            question.setOpd(null);
        }
        int row = questionMapper.updateQuestion(question);
        return row==1? true:false;
    }

    @Override
    public void delete(Integer questionId) {
        LambdaQueryWrapper<QuestionExams> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(QuestionExams::getQuestionId,questionId);
        List<QuestionExams> questionExams = questionExamsMapper.selectList(wrapper);
        if (questionExams!=null){
            throw new DeleteException("题目与考试管理无法删除");
        }
        questionMapper.deleteById(questionId);
    }
}
