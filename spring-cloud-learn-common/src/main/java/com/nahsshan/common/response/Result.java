package com.nahsshan.common.response;

import java.io.Serializable;

/**
 * restful接口通用返回结果
 */
public class Result<T> implements Serializable {

    /** 执行结果 */
    private boolean isSuccess;

    /** 错误码 */
    private String errorCode;

    /** 错误原因 */
    private String message;

    /** 返回数据 */
    private T data;

    /**
     * 返回成功的结果
     * @param <T>data 需返回的结果
     * @param message 返回信息
     * @return Result<T>
     */
    public static <T> Result<T> newSuccessResult(T data,String message){
        Result<T> result = new Result<>();
        result.isSuccess = true;
        result.data = data;
        result.message = message;
        return result;
    }

    /**
     * 返回成功的结果
     * @param data 需返回的结果
     * @param <T>
     * @return
     */
    public static <T> Result<T> newSuccessResult(T data){
        Result<T> result = new Result<>();
        result.isSuccess = true;
        result.data = data;
        return result;
    }

    /**
     * 返回成功的结果
     * @param message 成功信息
     * @return
     */
    public static <T> Result<T> newSuccessResult(String message){
        Result<T> result = new Result<>();
        result.isSuccess = true;
        result.message = message;
        return result;
    }

    /**
     * 返回成功的结果
     * @param <T>
     * @return
     */
    public static <T> Result<T> newSuccessResult(){
        Result<T> result = new Result<>();
        result.isSuccess = true;
        return result;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "isSuccess=" + isSuccess +
                ", errorCode=" + errorCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

