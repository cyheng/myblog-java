package com.doraro.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doraro.exception.ApiGlobalException;
import com.doraro.exception.ResourcesNotFoundException;
import com.doraro.model.entity.Article;
import com.doraro.model.param.ArchivesArticle;
import com.doraro.model.param.ArticleDetail;
import com.doraro.model.param.ArticleListData;
import com.doraro.model.param.ArticleParam;
import com.doraro.mapper.ArticleRepository;
import com.doraro.mapper.CategoryRepository;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * Created by cyheng on 2017/10/30.
 */
@Service
@Slf4j
public class ArticleService {
    private ArticleRepository articleRepository;
    private CategoryRepository categoryRepository;


    @Autowired
    public ArticleService(ArticleRepository articleRepository, CategoryRepository categoryRepository) {
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
    }

    public ArticleDetail getArticle(String id) {
//        if (Strings.isNullOrEmpty(id)) {
//            throw new ApiGlobalException("id不能为空");
//        }

        return Optional.ofNullable(articleRepository.findById(id))
                .orElseThrow(ResourcesNotFoundException::new);
    }

    public ArticleDetail getPublishedArticleById(String id) {
//        if (Strings.isNullOrEmpty(id)) {
//            throw new ApiGlobalException("id不能为空");
//        }
        return Optional.ofNullable(articleRepository.getPublishedArticleById(id))
                .orElseThrow(ResourcesNotFoundException::new);
    }


    public Page<ArticleListData> getPublishedArticleByPage(int pageNum, int pageSize, String categoryId) {
        Page<ArticleListData> page = new Page<>(pageNum, pageSize);
        return page.setRecords(articleRepository.getPushishedArticleByPage(page, categoryId));
    }

    public Article saveArticle(ArticleParam form) {
//        String id = form.getCategoryId();
//        Category category = categoryRepository.selectById(id);
//        if (category == null) {
//            throw new ResourcesNotFoundException(String.format("category id:%s not found!", id));
//        }
//        Article article = new Article();
//        article.setCategoryId(category.getId());
//        mapper.paramToEnity(form, article);
//        articleRepository.insert(article);
        return null;
    }

    public String deleteArticle(String id) {
//        if (Strings.isNullOrEmpty(id)) {
//            throw new ApiGlobalException("id不能为空");
//        }
        if (articleRepository.deleteById(id) == 0) {
            throw new ResourcesNotFoundException();
        }
        return id;

    }

    /***
     * TODO: order
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @param order
     * @return
     */
    public Page<ArticleDetail> findAll(int pageNum, int pageSize, String categoryId, String order) {
        boolean isAsc = false;
        if ("asc".equals(order)) {
            isAsc = true;
        }
        Page<ArticleDetail> page = new Page<>(pageNum, pageSize);
        return page.setRecords(articleRepository.getArticleByPage(page, categoryId));
    }

    @Transactional
    public String updateArticle(String id, ArticleParam param) {
//        Article article = articleRepository.selectById(id);
//        if (article == null) {
//            throw new ResourcesNotFoundException("article id " + id + " not found!");
//        }
//        Category category = categoryRepository.selectById(param.getCategoryId());
//        if (category == null) {
//            throw new ApiGlobalException(String.format("param categoryId:%s not found", param.getCategoryId()));
//        }
//        article.setCategoryId(category.getId());
//        mapper.paramToEnity(param, article);
//        articleRepository.updateById(article);
//        return article.getId();
        return "";
    }

    public Page<ArticleDetail> getAllDeleteArticle(int pageNum, int pageSize) {
        Page<ArticleDetail> page = new Page<>(pageNum, pageSize);
        page.setDesc("update_time");
        return page.setRecords(articleRepository.getAllDeleteArticle(page));
    }

    public Integer countNumbers() {
        return articleRepository.countArticles();
    }

    public Map<Integer, List<ArchivesArticle>> getArchives() {
        Page<ArchivesArticle> page = new Page<>(0, Integer.MAX_VALUE);
        page.setDesc("create_time");
        List<ArchivesArticle> archives = articleRepository.getArchives(page);
//        return archives.stream().collect(groupingBy((item) -> getYear(item.getCreateTime())));
        return null;
    }


}
