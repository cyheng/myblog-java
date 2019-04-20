package com.doraro.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ArticleStatusEnum implements IEnum<Integer> {
    /**
     * 文章状态枚举
     */
    DART(0, "草稿"),

    PUBLISH(1, "发布"),

    DELETE(2, "删除");

    @EnumValue
    private final int status;
    private final String descp;

    ArticleStatusEnum(int status, String descp) {
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
