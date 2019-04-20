package com.doraro.model.param;

import com.doraro.model.Convert;
import com.doraro.model.entity.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by cyheng on 2018/2/25.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CategoryParam extends Convert {
    @NotBlank(message = "categoryName不能为空", groups = {ValidateGroups.Create.class, ValidateGroups.Update.class})
    private String categoryName;
    @NotNull(message = "id不能为空", groups = ValidateGroups.Update.class)
    private Long id;

}
