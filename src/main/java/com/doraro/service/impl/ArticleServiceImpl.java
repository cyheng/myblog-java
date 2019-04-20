package com.doraro.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doraro.exception.beans.ErrorCodeEnum;
import com.doraro.mapper.CategoryMapper;
import com.doraro.model.dto.ArchivesArticleView;
import com.doraro.model.dto.ArticleDetail;
import com.doraro.model.entity.Article;
import com.doraro.model.enums.ArticleStatusEnum;
import com.doraro.model.param.ArticleListData;
import com.doraro.model.param.ArticleParam;
import com.doraro.mapper.ArticleMapper;
import com.doraro.service.IArticleService;
import com.doraro.utils.ApiAssert;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;


/**
 * Created by cyheng on 2017/10/30.
 */
@Service
@Slf4j
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ArticleDetail getArticle(Long id) {
        Validate.notNull(id, "文章id不能为空!");
        final Article article = super.getById(id);
        ApiAssert.notNull(ErrorCodeEnum.ARTICLE_NOT_FOUND, article);
        return article.convert(ArticleDetail.class);
    }

    @Override
    public ArticleDetail getPublishedArticleById(Long id) {
        Validate.notNull(id, "文章id不能为空!");
        final LambdaQueryWrapper<Article> query = new QueryWrapper<Article>().lambda()
                .eq(Article::getId, id)
                .eq(Article::getStatus, ArticleStatusEnum.PUBLISH);
        final Article article = this.baseMapper.selectOne(query);
        ApiAssert.notNull(ErrorCodeEnum.ARTICLE_NOT_FOUND, article);
        return article.convert(ArticleDetail.class);
    }


    @Override
    public IPage<ArticleListData> getPublishedArticleByPage(int pageNum, int pageSize, Long categoryId) {
        Page<ArticleListData> page = new Page<>(pageNum, pageSize);
        return super.baseMapper.getPublishedArticleByPage(page, categoryId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveArticle(ArticleParam form) {
        final Article article = form.convert(Article.class);

        return this.save(article);
    }

    /**
     * 软删除,将article的状态标记为delete
     * TODO: 由定时任务来真正删除文章,并删除分类关系
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean softDeleteArticle(Long id) {
        Validate.notNull(id, "id不能为空");
        final LambdaUpdateWrapper<Article> sql = new UpdateWrapper<Article>().lambda()
                .eq(Article::getId, id)
                .set(Article::getStatus, ArticleStatusEnum.DELETE);
        return super.update(sql);
    }


    @Override
    public IPage<ArticleDetail> findAllUnDelete(int pageNum, int pageSize, Long categoryId, Boolean des) {
        Page<Article> page = new Page<>(pageNum, pageSize);
        if (des == null || des) {
            page.setDesc(Article.UPDATE_TIME);
        } else {
            page.setAsc(Article.UPDATE_TIME);
        }
        return super.baseMapper.getUnDeleteArticleByPage(page, categoryId);


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateArticle(Long id, ArticleParam param) {
        Validate.notNull(id);
        final Article convert = param.convert(Article.class);
        convert.setId(id);
        return this.updateById(convert);
    }

    @Override
    public IPage<ArticleDetail> getAllDeleteArticle(int pageNum, int pageSize) {
        Page<ArticleDetail> page = new Page<>(pageNum, pageSize);
        page.setDesc("update_time");
        return super.baseMapper.getAllDeleteArticle(page);
    }

    @Override
    public Integer countNumbers() {
        return this.count(new QueryWrapper<Article>().lambda()
                .ne(Article::getStatus, ArticleStatusEnum.DELETE)
        );
    }

    @Override
    public Map<Integer, List<ArchivesArticleView>> getArchives() {
        final LambdaQueryWrapper<Article> queryWrapper = new QueryWrapper<Article>()
                .lambda()
                .eq(Article::getStatus, ArticleStatusEnum.PUBLISH)
                .orderByDesc(Article::getCreateTime);
        final List<Article> articles = super.getBaseMapper().selectList(queryWrapper);
        return articles.stream()
                .map(it -> it.convert(ArchivesArticleView.class))
                .collect(groupingBy((item) -> item.getCreateTime().getYear()));
    }


}
