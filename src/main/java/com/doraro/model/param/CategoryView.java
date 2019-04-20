package com.doraro.model.param;

import com.doraro.model.Convert;
import com.doraro.model.entity.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by cyheng on 2018/2/28.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CategoryView extends Convert {
    private String name;
    private Long id;
}
