package com.doraro.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusEnum implements IEnum<Integer> {
    /**
     * 状态枚举
     */
    DISABLE(0, "禁用"),

    NORMAL(1, "正常"),

    DELETE(2, "删除");

    @EnumValue
    private final int status;
    private final String descp;

    StatusEnum(int status, String descp) {
        this.status = status;
        this.descp = descp;
    }

    public int getStatus() {
        return status;
    }

    @JsonValue
    public String getDescp() {
        return descp;
    }


    @Override
    public Integer getValue() {
        return status;
    }
}
