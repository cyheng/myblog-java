package com.doraro.exception.beans;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class FailResponse extends ApiResponses {
    private static final long serialVersionUID = 1L;
    /**
     * 错误 状态码
     */
    private Integer status;
    /**
     * 错误状态码
     */
    private String error;
    /**
     * 错误帮助消息
     */
    private String msg;
    /**
     * 异常信息
     */
    private String exception;
    /**
     * 当前时间戳
     */
    private LocalDateTime time;
}
