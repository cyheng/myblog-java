package com.cyheng.model.VO;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created by cyheng on 2017/11/19.
 */
@Data
public class ArchivesArticle {
    private String id;
    private String title;
    @JsonFormat(pattern = "MM-dd")
    private Date createTime;
    @JsonFormat(pattern = "MM-dd")
    private Date updateTime;
}
