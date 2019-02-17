package com.doraro.model.param;

import com.doraro.model.Convert;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by cyheng on 2018/2/25.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CategoryParam extends Convert {
    @NotBlank
    private String name;
    @NotBlank
    private String id;
}
