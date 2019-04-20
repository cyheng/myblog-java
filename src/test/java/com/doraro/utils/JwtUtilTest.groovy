package com.doraro.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.auth0.jwt.interfaces.JWTVerifier
import com.doraro.controller.SuperMockMvcSetup
import spock.lang.Specification

import java.time.Instant
import java.time.temporal.ChronoUnit

class JwtUtilTest extends Specification {
    def test1() {
        when:
        def d = Date.from(Instant.now().plus(30, ChronoUnit.MINUTES))
        then:
        println(d)
    }

    def test2() {
        when:
        def d = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJuYmYiOjE1NTIyMDc2MDgsImV4cCI6MTU1MjIwNzYxMSwiaWF0IjoxNTUyMjA3NjA4LCJqdGkiOiI0YTUwMThiNy1jNDhlLWMyNjUtZTlmYi0wYmJhN2UyN2YzODMiLCJ1c2VybmFtZSI6ImEifQ.BYvHpIkMiC0fjIRQHiQoybWat_HkjyOrjmr399xOmcU7qXf1MNA2N19xFwzneSvm6q9gv6tQfkN55d6yESW7tw'
        then:
        DecodedJWT jwt = JWT.decode(d)
        println(jwt.header)
        println(jwt.payload)
        println(jwt.signature)
        println(jwt.token)


    }
}
