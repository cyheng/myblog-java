package com.doraro.model.dto;


import com.doraro.model.Convert;
import com.doraro.model.entity.BaseModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by cyheng on 2017/11/19.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ArchivesArticleView extends Convert {
    private Long id;
    private String title;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
