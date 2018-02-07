package com.chenyingjun.sp.common.exception;


import com.chenyingjun.sp.common.bean.ExceptionType;

/**
 * 业务异常
 *
 * @author chenyingjun
 * @since 1.0.0
 *
 * @see ExceptionType
 */
public class BusinessException extends RuntimeException {

    private int code;

    private String message;

    public BusinessException(ExceptionType exceptionType) {
        this.code = exceptionType.getCode();
        this.message = exceptionType.getMessage();
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
