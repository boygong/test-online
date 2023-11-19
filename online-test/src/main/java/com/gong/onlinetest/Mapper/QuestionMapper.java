package com.gong.onlinetest.Mapper;

import com.gong.onlinetest.Pojo.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */
@Mapper
public interface QuestionMapper {
    @Insert("insert into questions(content, type, opa, opb, opc, opd, correntAnswer)" +
            "values (#{content},#{type},#{opa},#{opb},#{opc},#{opd},#{currentAnswer})")
    public int InsertQuestion(Question question);

    @Select("select id, content, type, opa, opb, opc, opd, correntAnswer from questions")
    List<Question> SelectAllQuestion();

    @Update("update questions set content=#{content},type=#{type}," +
            "opa=#{opa},opb=#{opb},opc=#{opc},opd=#{opd},correntAnswer=#{currentAnswer} where id=#{id}")
    int updateQuestion(Question question);
}
