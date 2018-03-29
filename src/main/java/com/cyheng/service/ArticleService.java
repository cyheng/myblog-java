package com.cyheng.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.cyheng.exception.CheckException;
import com.cyheng.exception.ResourcesNotFoundException;
import com.cyheng.model.DO.Article;
import com.cyheng.model.DO.Category;
import com.cyheng.model.Mapper.ArticleMapper;
import com.cyheng.model.VO.ArchivesArticle;
import com.cyheng.model.VO.ArticleDetail;
import com.cyheng.model.VO.ArticleListData;
import com.cyheng.model.VO.ArticleParam;
import com.cyheng.repository.ArticleRepository;
import com.cyheng.repository.CategoryRepository;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.groupingBy;


/**
 * Created by cyheng on 2017/10/30.
 */
@Service
@Slf4j
public class ArticleService {
    private ArticleRepository articleRepository;
    private CategoryRepository categoryRepository;
    private ArticleMapper mapper;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, CategoryRepository categoryRepository, ArticleMapper mapper) {
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    public ArticleDetail getArticle(String id) {
        if (Strings.isNullOrEmpty(id)) {
            throw new CheckException("id不能为空");
        }

        return Optional.ofNullable(articleRepository.findById(id))
                .orElseThrow(ResourcesNotFoundException::new);
    }

    public ArticleDetail getPublishedArticleById(String id) {
        if (Strings.isNullOrEmpty(id)) {
            throw new CheckException("id不能为空");
        }
        return Optional.ofNullable(articleRepository.getPublishedArticleById(id))
                .orElseThrow(ResourcesNotFoundException::new);
    }


    public Page<ArticleListData> getPublishedArticleByPage(int pageNum, int pageSize, String categoryId) {
        Page<ArticleListData> page = new Page<>(pageNum, pageSize, "update_time", false);
        return page.setRecords(articleRepository.getPushishedArticleByPage(page, categoryId));
    }

    public Article saveArticle(ArticleParam form) {
        String id = form.getCategoryId();
        Category category = categoryRepository.selectById(id);
        if (category == null) {
            throw new ResourcesNotFoundException(String.format("category id:%s not found!", id));
        }
        Article article = new Article();
        article.setCategoryId(category.getId());
        mapper.paramToEnity(form, article);
        articleRepository.insert(article);
        return article;
    }

    public String deleteArticle(String id) {
        if (Strings.isNullOrEmpty(id)) {
            throw new CheckException("id不能为空");
        }
        if (articleRepository.deleteById(id) == 0) {
            throw new ResourcesNotFoundException();
        }
        return id;

    }

    public Page<ArticleDetail> findAll(int pageNum, int pageSize, String categoryId, String order) {
        boolean isAsc = false;
        if ("asc".equals(order)) {
            isAsc = true;
        }
        Page<ArticleDetail> page = new Page<>(pageNum, pageSize, "update_time", isAsc);
        return page.setRecords(articleRepository.getArticleByPage(page, categoryId));
    }

    @Transactional
    public String updateArticle(String id, ArticleParam param) {
        Article article = articleRepository.selectById(id);
        if (article == null) {
            throw new ResourcesNotFoundException("article id " + id + " not found!");
        }
        Category category = categoryRepository.selectById(param.getCategoryId());
        if (category == null) {
            throw new CheckException(String.format("param categoryId:%s not found", param.getCategoryId()));
        }
        article.setCategoryId(category.getId());
        mapper.paramToEnity(param, article);
        articleRepository.updateById(article);
        return article.getId();

    }

    public Page<ArticleDetail> getAllDeleteArticle(int pageNum, int pageSize) {
        Page<ArticleDetail> page = new Page<>(pageNum, pageSize, "update_time");
        return page.setRecords(articleRepository.getAllDeleteArticle(page));
    }

    public Integer countNumbers() {
        return articleRepository.countArticles();
    }

    public Map<Integer, List<ArchivesArticle>> getArchives() {
        Page<ArchivesArticle> page = new Page<>(0, Integer.MAX_VALUE, "create_time", false);
        List<ArchivesArticle> archives = articleRepository.getArchives(page);
        return archives.stream().collect(groupingBy((item) -> getYear(item.getCreateTime())));
    }

    private int getYear(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate().getYear();
    }
}
