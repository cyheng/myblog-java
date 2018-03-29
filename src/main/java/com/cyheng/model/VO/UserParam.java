package com.cyheng.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by cyheng on 2018/2/24.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserParam {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
