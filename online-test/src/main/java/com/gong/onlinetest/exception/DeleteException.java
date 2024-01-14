package com.gong.onlinetest.exception;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

/**
 * @author 龚圆康
 * @class 软件2144
 * @student_number 21040147
 * @Time 2024/1/14
 */
@Data
@Tag(name = "自定义异常类")
public class DeleteException extends RuntimeException{
    private String message;
    public DeleteException(String message){
        this.message = message;
    }
    public DeleteException(){}
}
