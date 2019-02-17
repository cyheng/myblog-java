package com.doraro.model.param;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Data
public class TestParam {
    @NotNull
    Gender gender;

    public enum Gender {
        MALE, FEMALE;
    }
}
