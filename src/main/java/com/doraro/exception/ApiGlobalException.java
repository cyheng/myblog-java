package com.doraro.exception;

import com.doraro.exception.beans.ErrorCode;
import com.doraro.exception.beans.ErrorCodeEnum;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by cyheng on 2018/2/27.
 */

public class ApiGlobalException extends RuntimeException {

    /**
     * 错误码
     */
    private final ErrorCode errorCode;

    public ApiGlobalException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMsg());
        this.errorCode = errorCodeEnum.convert();
    }

    public ApiGlobalException(String msg) {
        super(msg);
        this.errorCode = ErrorCode.builder().msg(msg).httpCode(400).build();
    }

    public ApiGlobalException(ErrorCode errorCode) {
        super(errorCode.getError());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }


}