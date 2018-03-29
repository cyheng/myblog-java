package com.cyheng.model.VO;

import lombok.Data;

import java.util.Date;

/**
 * Created by cyheng on 2018/1/11.
 */
@Data
public class ArticleListData {
    private String id;
    private String title;
    private String summary;
    private Date updateTime;
    private CategoryView category;
}
