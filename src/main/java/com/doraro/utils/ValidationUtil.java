package com.doraro.utils;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cyheng on 2018/3/1.
 */
public class ValidationUtil {
    public static String fromBindingErrors(Errors errors) {
        List<String> validErrors = new ArrayList<>();
        for (FieldError fieldError : errors.getFieldErrors()) {
            validErrors.add(fieldError.getField() + " " + fieldError.getDefaultMessage());
        }
        return String.join("\n", validErrors);
    }
}
