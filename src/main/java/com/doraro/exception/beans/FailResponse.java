package com.doraro.exception.beans;

import com.doraro.utils.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class FailResponse extends ApiResponses {
    private static final long serialVersionUID = 1L;
    /**
     * 错误 状态码
     */
    private Integer code;
    /**
     * 错误状态码
     */
    private String error;
    /**
     * 错误帮助消息
     */
    private String msg;
    /**
     * 当前时间戳
     */
    private LocalDateTime time;
}
