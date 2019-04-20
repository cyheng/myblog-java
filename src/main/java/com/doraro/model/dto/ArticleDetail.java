package com.doraro.model.dto;

import com.doraro.model.entity.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.List;

import com.doraro.model.entity.Category;
import com.doraro.model.enums.ArticleStatusEnum;
import com.doraro.model.param.CategoryView;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author doraro
 * @since 2019-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleDetail extends BaseModel {

    private static final long serialVersionUID = 1L;


    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String content;

    private String title;

    private String summary;

    private Boolean allowComment;

    private Boolean showToc;

    private ArticleStatusEnum status;

    private List<Category> categories;

}
