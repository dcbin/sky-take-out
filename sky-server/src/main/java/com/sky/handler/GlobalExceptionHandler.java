package com.sky.handler;

import com.sky.exception.BaseException;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        String message = ex.getMessage();
        String[] split = message.split(" ");

        // 手机号重复异常
        if(split[5].equals("'employee.phone'")) {
            String msg = "手机号" + split[2] + "重复";
            return Result.error(msg);
        }
        // 用户名重复
        if(split[5].equals("'employee.idx_username'")) {
            String msg = "用户名" + split[2] + "重复";
            return Result.error(msg);
        }
        // 分类名重复
        if(split[5].equals("'category.idx_category_name'")) {
            String msg = "分类名" + split[2] + "重复";
            return Result.error(msg);
        }
        return Result.error(message);
    }
}
