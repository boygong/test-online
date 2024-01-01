package com.gong.onlinetest.task;

import com.gong.onlinetest.Mapper.ExamMapper;
import com.gong.onlinetest.Mapper.StudentExamMapper;
import com.gong.onlinetest.Pojo.StudentExam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */
@Slf4j
@Component
public class testTask {

    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private StudentExamMapper studentExamMapper;

    @Scheduled(cron = "0 * * * * ?")//每分钟执行一次
    public void processTimeTest(){
      log.info("定时处理超时的学生考试信息:{}", LocalDateTime.now());
        LocalDateTime now = LocalDateTime.now();//获取当前时间
        List<Integer> ids = examMapper.getByExpireTime(now);
        for (Integer id : ids) {
            //在student_exams表中修改state状态
            List<StudentExam> SExams= studentExamMapper.getByExamId(id);
            for (StudentExam sExam : SExams) {
                //修改学生考试信息
                if (sExam.getState().equals("已完成")&& sExam.getState().equals("已过期")){
                    sExam.setState("已过期");
                    studentExamMapper.update(sExam);
                }
            }
        }
    }
}
