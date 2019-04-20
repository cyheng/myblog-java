package com.doraro.model.param;

import com.doraro.model.enums.StatusEnum;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
public class Test4Param {
    StatusEnum status;
    Gender gender;
    LocalDateTime localDateTime;
    LocalDate localDate;
    LocalTime localTime;
    Date timeStamp;

    public enum Gender {
        MALE, FEMALE;
    }
}
