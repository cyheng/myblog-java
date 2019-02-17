package com.doraro.exception.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 返回结果的父类,仅提供静态方法
 *
 * @author doraro
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponses implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 带数据的成功返回
     *
     * @param data
     * @return
     */
    public static ApiResponses ok(Object data) {
        final HttpStatus ok = HttpStatus.OK;
        return SuccessResponse.builder()
                .code(ok.value())
                .message(ok.name())
                .data(data)
                .build();
    }

    /***
     * 不带数据的成功返回
     * @return
     */
    public static ApiResponses ok() {
        return ok(null);
    }


    /**
     * 失败返回
     *
     * @param errorCode 异常枚举
     * @param exception 异常信息
     */
    public static ApiResponses failure(ErrorCodeEnum errorCode, String exception) {
        return failure(errorCode.convert(), null, exception);
    }

    public static ApiResponses failure(ErrorCode errorCode, String exception) {
        return failure(errorCode, null, exception);
    }


    private static ApiResponses failure(ErrorCode errorCode, String message, String exception) {
        return FailResponse.builder()
                .error(errorCode.getError())
                .msg(message)
                .exception(exception)
                .time(LocalDateTime.now())
                .status(errorCode.getHttpCode())
                .build();
    }


}
