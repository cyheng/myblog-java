package com.doraro.model.entity;


import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * Created by cyheng on 2017/10/15.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Article extends BaseModel {

    @JsonIgnore
    @TableLogic
    private Integer isDelete = 0;
    private String content;
    private String status;
    private String title;
    private String summary;
    private String categoryId;
    private boolean allowComment;
    private boolean showToc;//显示导航
}
