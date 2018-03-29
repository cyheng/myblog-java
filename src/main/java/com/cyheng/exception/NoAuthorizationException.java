package com.cyheng.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by cyheng on 2018/2/19.
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "未认证")
public class NoAuthorizationException extends RuntimeException {

}
