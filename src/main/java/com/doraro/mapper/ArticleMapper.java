package com.doraro.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doraro.model.dto.ArchivesArticleView;
import com.doraro.model.dto.ArticleDetail;
import com.doraro.model.entity.Article;
import com.doraro.model.param.ArticleListData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by cyheng on 2017/10/15.
 */


@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    IPage<ArticleDetail> getUnDeleteArticleByPage(IPage page, @Param("categoryId") Long categoryId);

    IPage<ArticleDetail> getAllDeleteArticle(IPage page);


    IPage<ArticleListData> getPublishedArticleByPage(IPage page, @Param("categoryId") Long categoryId);
}
