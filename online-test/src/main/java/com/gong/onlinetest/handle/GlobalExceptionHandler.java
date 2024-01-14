package com.gong.onlinetest.handle;

import com.gong.onlinetest.Pojo.Result;
import com.gong.onlinetest.exception.DeleteException;
import jakarta.websocket.DecodeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 龚圆康
 * @class 软件2144
 * @student_number 21040147
 * @Time 2024/1/14
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(DeleteException.class)
    @ResponseBody
    public Result deleteError(DeleteException e){
        e.printStackTrace();
        return Result.error(e.getMessage());
    }
}
