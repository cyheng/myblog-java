package com.doraro.model.param;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class PageParam {
    @ApiParam(value = "页号", example = "1", type = "Integer")
    private Integer page = 1;
    @ApiParam(value = "页大小", example = "5", type = "Integer")
    private Integer size = 5;
}
