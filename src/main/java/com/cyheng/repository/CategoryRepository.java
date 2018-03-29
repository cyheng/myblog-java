package com.cyheng.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.cyheng.model.DO.Category;
import com.cyheng.model.VO.ArticleDetail;
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

    List<Category> getCategoryByPage(Pagination page);

    Integer countCategory();
}
