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


    private static ApiResponses buildFailure(ErrorCode errorCode, String message) {
        return FailResponse.builder()
                .error(errorCode.getError())
                .msg(message)
                .time(LocalDateTime.now())
                .code(errorCode.getHttpCode())
                .build();
    }


    public static ApiResponses failure(ErrorCodeEnum errorCode) {
        return failure(errorCode.convert());
    }

    public static ApiResponses failure(String msg) {
        return failure(400, msg);
    }

    public static ApiResponses failure(int code, String msg) {
        final ErrorCode errorCode = ErrorCode.builder().msg(msg).httpCode(code).build();
        return buildFailure(errorCode, msg);
    }

    public static ApiResponses failure(ErrorCode badRequest) {
        return buildFailure(badRequest, badRequest.getMsg());
    }
}
