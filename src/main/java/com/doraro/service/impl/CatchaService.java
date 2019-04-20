package com.doraro.service.impl;


import com.doraro.redis.RedisKey;
import com.doraro.redis.RedisPrefix;
import com.doraro.redis.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatchaService {
    @Autowired
    private RedisUtils redisUtils;

    public void saveCaptcha(String uuid, String captcha) {
        final RedisKey keys = RedisKey.build(RedisPrefix.SYS_CAP, uuid);
        redisUtils.set(keys, captcha);
    }

    public boolean checkCaptcha(String uuid, String captcha) {
        if (StringUtils.isBlank(uuid) || StringUtils.isBlank(captcha)) {
            return false;
        }
        final RedisKey keys = RedisKey.build(RedisPrefix.SYS_CAP, uuid);
        final String code = redisUtils.get(keys.getRealKey());
        if (code == null || !code.equals(captcha)) {
            return false;
        }
        redisUtils.delete(keys.getRealKey());
        return true;
    }
}
