package com.doraro.model.param;

import com.doraro.model.Convert;
import com.doraro.model.entity.BaseModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SinglePageParam extends Convert {
    @NotNull
    private String id;
    @NotNull
    private String content;
}
