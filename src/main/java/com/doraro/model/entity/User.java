package com.doraro.model.entity;

import lombok.*;

/**
 * Created by cyheng on 2018/2/24.
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class User extends BaseModel {

    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String role;
    private String introduction;
    private String avatar;
}
