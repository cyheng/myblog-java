package com.doraro.model.param;

import com.doraro.model.Convert;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by cyheng on 2018/2/25.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetail extends Convert {
    private String id;
    private String title;
    private String summary;
    private String status;
    private String content;//存markdown
    private boolean allowComment;
    private boolean showToc;//显示导航
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private CategoryView category;
}
