package com.cyheng.model.VO;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by cyheng on 2018/2/25.
 */
@Data
public class CategoryParam {
    @NotBlank
    private String name;
    @NotBlank
    private String id;
}
