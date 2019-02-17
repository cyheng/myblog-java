package com.doraro.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.doraro.model.entity.Category;
import com.doraro.model.param.ArticleDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cyheng on 2017/10/29.
 */
@Mapper
@Repository
public interface CategoryRepository extends BaseMapper<Category> {
    List<ArticleDetail> getArticles(@Param("id") String categoryId);

    Category findCategoryByName(String name);

    Integer getArticleNum(@Param("id") String categoryId);

    List<Category> getCategoryByPage(IPage page);

    Integer countCategory();
}
