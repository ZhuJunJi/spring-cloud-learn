package com.nahsshan.common.exception;

import com.nahsshan.common.response.Result;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 *  @author J.zhu
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler<T> {

    /**
     * 请求方法不正确
     * @param exception
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        throw new CommonBizException(ExpCodeEnum.HTTP_REQ_METHOD_ERROR);
    }

    @ExceptionHandler(value=CommonBizException.class)
    public Result<T> commonBizException(HttpServletRequest request, CommonBizException e){
        return Result.newFailureResult(e);
    }

    @ExceptionHandler(value=CommonSysException.class)
    public Result<T> commonSysException(HttpServletRequest request, CommonSysException e){
        return Result.newFailureResult(e);
    }

    @ExceptionHandler(value=Exception.class)
    public Result<T> exception(HttpServletRequest request, Exception e){
        return Result.newFailureResult(e.getMessage());
    }
}
