package com.doraro.model.param;

import com.doraro.model.Convert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class UserDetail extends Convert {
    private String name;
    @NonNull
    private String role;
    private String introduction;
    private String avatar;
}
