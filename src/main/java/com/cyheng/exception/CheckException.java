package com.cyheng.exception;

/**
 * Created by cyheng on 2018/2/27.
 */

public class CheckException extends RuntimeException {


    public CheckException() {
    }

    public CheckException(String message) {
        super(message);
    }

    public CheckException(Throwable cause) {
        super(cause);
    }

    public CheckException(String message, Throwable cause) {
        super(message, cause);
    }


}