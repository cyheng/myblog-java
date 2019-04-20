package com.doraro.model.param;

import com.doraro.model.Convert;
import com.doraro.model.entity.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserDetail extends Convert {
    private Long id;
    private String name;
    private List<String> roles;
    private String avatar;
}
