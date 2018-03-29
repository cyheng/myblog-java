package com.cyheng.model.VO;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SinglePageParam {
    @NotNull
    private String id;
    @NotNull
    private String content;
}
