package com.cyheng.model.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultBean {
    private String message = "SUCCESS";
    private Object data;
    private STATUS code = STATUS.SUCCESS;

    public ResultBean(String message, STATUS code) {
        this.message = message;
        this.code = code;
    }
    public static ResultBean ok(Object data) {
        return new ResultBean("SUCCESS", data, STATUS.SUCCESS);
    }

    public static ResultBean ok() {
        return new ResultBean("SUCCESS", STATUS.SUCCESS);
    }

    public enum STATUS {
        SUCCESS, FAIL, VALIDATIONERROR, NOTFOUND
    }
}
