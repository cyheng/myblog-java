package com.doraro.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author doraro
 * @since 2019-03-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("category_article")
public class CategoryArticle extends BaseModel {

    private static final long serialVersionUID = 1L;

    private Long articleId;

    private Long categoryId;


    public static final String ARTICLE_ID = "article_id";

    public static final String CATEGORY_ID = "category_id";

}
