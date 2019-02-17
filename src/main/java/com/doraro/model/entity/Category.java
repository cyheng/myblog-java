package com.doraro.model.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * Created by cyheng on 2017/10/15.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Accessors(chain = true)

public class Category extends BaseModel {

    @JsonIgnore
    @TableLogic
    private Integer isDelete = 0;

    @NonNull
    @TableField("category_name")
    private String name;


}
