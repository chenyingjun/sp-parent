package com.chenyingjun.sp.common.bean;

/**
 * 异常类型.
 *
 * @author chenyingjun
 * @since 1.0.0
 *
 * @see com.chenyingjun.sp.common.exception.BusinessException
 */
public enum ExceptionType {

    AUTHORIZATION_ERROR(401, "请先登录"),
    DATA_NOT_FOUND(404, "数据未找到"),
    PARAMETER_ILLEGAL(403, "参数无效"),
    USER_NOT_FOUND(602, "用户不存在"),
    USERNAME_PASSWORD_ERROR(601, "用户名或密码错误"),
    ;

    private int code;

    private String message;

    ExceptionType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
