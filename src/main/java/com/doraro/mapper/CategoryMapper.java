package com.doraro.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.doraro.model.dto.ArticleDetail;
import com.doraro.model.entity.Category;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cyheng on 2017/10/29.
 */
@Repository
public interface CategoryMapper extends BaseMapper<Category> {
    List<ArticleDetail> getArticles(@Param("articleId") Long articleId);

    Category findCategoryByName(@Param("name") String name);

    Integer getArticleNum(@Param("categoryId") Long categoryId);


    Integer linkCategoryAndArticle(@Param("categoryId") Long categoryId, @Param("articleId") Long articleId);

    List<Category> findCategoriesByArticleId(@Param("articleId") Long articleId);
}
