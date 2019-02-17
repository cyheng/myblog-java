package com.doraro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by cyheng on 2018/2/19.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "没有此资源")
public class ResourcesNotFoundException extends RuntimeException {
    public ResourcesNotFoundException(String msg) {
        super(msg);
    }

    public ResourcesNotFoundException() {
    }
}
