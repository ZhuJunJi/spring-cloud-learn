package com.nahsshan.common.exception;

import java.io.Serializable;

/**
 * 通用系统异常
 *  @author J.zhu
 */
public class CommonSysException extends RuntimeException implements Serializable {

    /**
     * 异常枚举
     */
    private ExpCodeEnum codeEnum;

    public CommonSysException(ExpCodeEnum codeEnum) {
        super(codeEnum.getMessage());
        this.codeEnum = codeEnum;
    }

    public CommonSysException() {

    }

    public ExpCodeEnum getCodeEnum() {
        return codeEnum;
    }
}
