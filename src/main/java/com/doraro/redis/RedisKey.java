package com.doraro.redis;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class RedisKey {
    private RedisPrefix prefix;
    @Getter
    private String realKey;

    public static RedisKey build(RedisPrefix prefix, String key) {
        return new RedisKey(prefix, prefix.getPrefix() + key);
    }

    public long getExpireTime() {
        return prefix.getExpireTime();
    }

}
