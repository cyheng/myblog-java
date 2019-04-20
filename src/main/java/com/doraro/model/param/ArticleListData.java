package com.doraro.model.param;

import com.doraro.model.Convert;
import com.doraro.model.entity.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by cyheng on 2018/1/11.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ArticleListData extends Convert {
    private Long id;
    private String title;
    private String summary;
    private LocalDateTime updateTime;
    private CategoryView category;
}
