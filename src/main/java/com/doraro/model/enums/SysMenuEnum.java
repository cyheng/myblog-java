package com.doraro.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SysMenuEnum implements IEnum<Integer> {
    /**
     * 菜单状态枚举
     */
    DIR(0, "目录"),

    MENU(1, "菜单"),

    BUTTON(2, "按钮");

    @EnumValue
    private final int status;
    private final String descp;

    SysMenuEnum(int status, String descp) {
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
