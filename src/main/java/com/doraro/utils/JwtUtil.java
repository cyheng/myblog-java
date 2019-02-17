package com.doraro.utils;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by cyheng on 2017/10/22.
 */
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.sessionTime}")
    private int sessionTime;

    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public static String extractToken(String authorization) throws IllegalArgumentException {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new IllegalArgumentException();
        }
        String token = authorization.replaceAll("Bearer", "").trim();
        if (Strings.isNullOrEmpty(token)) {
            throw new IllegalArgumentException();
        }
        return token;
    }

    public Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(this.secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public String toToken(String id) {
        return Jwts.builder()
                .setId(id)
                .setExpiration(expireTimeFromNow())
                .signWith(SIGNATURE_ALGORITHM, secret)
                .compact();
    }

    private Date expireTimeFromNow() {
        return new Date(System.currentTimeMillis() + sessionTime * 1000);
    }
}
