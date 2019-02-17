package com.doraro.exception.beans;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorCode {
    private final int httpCode;
    private final String msg;
    private final String error;
}
