package com.cyheng.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.cyheng.model.DO.Article;
import com.cyheng.model.VO.ArchivesArticle;
import com.cyheng.model.VO.ArticleDetail;
import com.cyheng.model.VO.ArticleListData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by cyheng on 2017/10/15.
 */


@Mapper
@Repository
public interface ArticleRepository extends BaseMapper<Article> {

    ArticleDetail getPublishedArticleById(String id);

    List<ArticleListData> getPushishedArticleByPage(Pagination page, String categoryId);

    List<ArticleDetail> getArticleByPage(Pagination page, String categoryId);

    List<ArticleDetail> getAllDeleteArticle(Pagination page);

    ArticleDetail findById(String id);

    Integer countArticles();

    List<ArchivesArticle> getArchives(Pagination page);
}
