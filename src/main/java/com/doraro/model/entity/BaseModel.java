package com.doraro.model.entity;


import com.doraro.model.Convert;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * Created by cyheng on 2018/2/27.
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class BaseModel extends Convert {

    private Long id;


}
