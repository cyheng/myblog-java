package com.cyheng.model.DO;

import com.baomidou.mybatisplus.annotations.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class SinglePage extends BaseModel {
    @NonNull
    private String slug;
    @NonNull
    private String content;
    @JsonIgnore
    @TableLogic
    private Integer isDelete = 0;

}
