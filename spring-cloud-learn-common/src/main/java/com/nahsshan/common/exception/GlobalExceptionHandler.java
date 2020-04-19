package com.nahsshan.common.exception;

import com.nahsshan.common.response.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 *  @author J.zhu
 */
@Component
public class GlobalExceptionHandler<T> {

    @ExceptionHandler(value=CommonBizException.class)
    public Result<T> exceptionHandler(HttpServletRequest request, CommonBizException e){
        return Result.newFailureResult(e);
    }

    @ExceptionHandler(value=CommonSysException.class)
    public Result<T> exceptionHandler(HttpServletRequest request, CommonSysException e){
        return Result.newFailureResult(e);
    }

    @ExceptionHandler(value=Exception.class)
    public Result<T> exceptionHandler(HttpServletRequest request, Exception e){
        return Result.newFailureResult(e.getMessage());
    }
}
