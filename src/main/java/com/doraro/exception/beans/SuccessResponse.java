package com.doraro.exception.beans;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * 成功赶回
 *
 * @author doraro
 */
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class SuccessResponse extends ApiResponses {
    /**
     * 成功描述信息
     */
    private String message;
    /**
     * 成功数据
     */
    private Object data;
    /**
     * 成功状态码,通常为http code
     */
    private Integer code;

    public SuccessResponse(String message, Object data, Integer code) {
        this.message = message;
        this.data = data;
        this.code = code;
    }

}
