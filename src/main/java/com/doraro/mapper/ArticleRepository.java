package com.doraro.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.doraro.model.entity.Article;
import com.doraro.model.param.ArchivesArticle;
import com.doraro.model.param.ArticleDetail;
import com.doraro.model.param.ArticleListData;
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

    List<ArticleListData> getPushishedArticleByPage(IPage page, String categoryId);

    List<ArticleDetail> getArticleByPage(IPage page, String categoryId);

    List<ArticleDetail> getAllDeleteArticle(IPage page);

    ArticleDetail findById(String id);

    Integer countArticles();

    List<ArchivesArticle> getArchives(IPage page);
}
