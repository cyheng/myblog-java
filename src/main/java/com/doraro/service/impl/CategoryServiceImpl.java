package com.doraro.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doraro.exception.ApiGlobalException;
import com.doraro.exception.beans.ErrorCodeEnum;
import com.doraro.mapper.CategoryMapper;
import com.doraro.model.dto.ArticleDetail;
import com.doraro.model.entity.Category;
import com.doraro.model.enums.ArticleStatusEnum;
import com.doraro.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author doraro
 * @since 2019-03-01
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Override
    public List<ArticleDetail> getArticles(Long articleId) {
        Validate.notNull(articleId);
        final List<ArticleDetail> articles = this.baseMapper.getArticles(articleId);
        return articles.stream()
                .filter(articleDetail -> articleDetail.getStatus() != ArticleStatusEnum.DELETE)
                .collect(Collectors.toList());
    }

    @Override
    public IPage<Category> getCategoryByPage(int pageNum, int pageSize) {
        Page<Category> page = new Page<>(pageNum, pageSize);
        page.setDesc("update_time");
        return this.page(page);
    }

    @Override
    public boolean deleteCategoryById(Long id) {
        Integer articleNum = super.baseMapper.getArticleNum(id);
        if (articleNum != 0) {
            throw new ApiGlobalException(ErrorCodeEnum.CATEGORY_HAS_ARTICLE);
        }
        //TODO:
        //该分类的所有文章id
        //若仅有状态为正删除的文章,且不强制删除
        //删除关联,然后删除标签(回收站回复的文章需要重新关联标签)
        //否则直接删除
        return super.removeById(id);
    }
}
