package com.doraro.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.vip.vjtools.vjkit.id.IdUtil;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

/**
 * Created by cyheng on 2017/10/22.
 */
@Component
public class JwtUtil {


    private static final int SESSION_TIME = 30;
    private static final TemporalUnit UNIT = ChronoUnit.MINUTES;
    private static final String salt = "1MjIwNzYxMSwiaWF0IjoxNTUyMjA3NjA4LCJqdG";

    /**
     * 通过用户id生成token
     * 密码作为JWT的盐
     *
     * @param userId
     * @return
     */
    public static String toToken(Long userId) {
        final Date now = new Date();
        Algorithm algorithm = Algorithm.HMAC512(salt);
        return JWT.create()
                .withClaim("id", userId)
                .withJWTId(IdUtil.fastUUID().toString())
                .withExpiresAt(expireTimeFromNow())
                .withNotBefore(now)
                .withIssuedAt(now)
                .sign(algorithm);

    }

    /**
     * 验证JWT是否合法
     *
     * @param token
     * @param userId
     * @return
     */
    public static boolean verify(String token, Long userId) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(salt);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("id", userId)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    private static Date expireTimeFromNow() {
        return Date.from(Instant.now().plus(SESSION_TIME, UNIT));
    }

    /**
     * 获取JWT中的claim中的用户ID
     * claim是公开的,因此不用验证
     *
     * @param token
     * @return
     */
    public static Long getUserId(String token) {
        try {

            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("id").asLong();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
