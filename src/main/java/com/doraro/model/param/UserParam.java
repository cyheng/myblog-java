package com.doraro.model.param;

import com.doraro.model.Convert;
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
public class UserParam extends Convert {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
