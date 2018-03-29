package com.cyheng.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class UserDetail {
    private String name;
    @NonNull
    private String role;
    private String introduction;
    private String avatar;
}
