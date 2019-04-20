package com.doraro.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.doraro.model.dto.ArchivesArticleView;
import com.doraro.model.dto.ArticleDetail;
import com.doraro.model.param.ArticleListData;
import com.doraro.model.param.ArticleParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface IArticleService {
    com.doraro.model.dto.ArticleDetail getArticle(Long id);

    com.doraro.model.dto.ArticleDetail getPublishedArticleById(Long id);

    IPage<ArticleListData> getPublishedArticleByPage(int pageNum, int pageSize, Long categoryId);

    boolean saveArticle(ArticleParam form);

    boolean softDeleteArticle(Long id);

    IPage<ArticleDetail> findAllUnDelete(int pageNum, int pageSize, Long categoryId, Boolean des);

    @Transactional
    Boolean updateArticle(Long id, ArticleParam param);

    IPage<ArticleDetail> getAllDeleteArticle(int pageNum, int pageSize);

    Integer countNumbers();

    Map<Integer, List<ArchivesArticleView>> getArchives();
}
