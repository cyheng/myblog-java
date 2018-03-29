package com.cyheng.model.DO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by cyheng on 2018/2/24.
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User extends BaseModel {

    private String name;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String role;
    private String introduction;
    private String avatar;
}
