package com.doraro.redis;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RedisPrefix {
    /**
     * 系统登录验证码,5分钟
     */
    SYS_CAP("sys:cap:", 300);
    private final String prefix;
    private final long expireTime;
}