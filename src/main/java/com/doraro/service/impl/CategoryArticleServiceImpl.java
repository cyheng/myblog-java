package com.doraro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.doraro.model.entity.CategoryArticle;
import com.doraro.mapper.CategoryArticleMapper;
import com.doraro.service.ICategoryArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author doraro
 * @since 2019-03-04
 */
@Service
public class CategoryArticleServiceImpl extends ServiceImpl<CategoryArticleMapper, CategoryArticle> implements ICategoryArticleService {

    @Override
    public boolean saveCategoryArticle(Long articleId, List<Long> categoryIds) {
        super.remove(new QueryWrapper<CategoryArticle>().lambda()
                .eq(CategoryArticle::getArticleId, articleId)
        );
        final Set<CategoryArticle> collect = categoryIds.stream()
                .map(cid -> new CategoryArticle().setArticleId(articleId).setCategoryId(cid))
                .collect(Collectors.toSet());
        return this.saveBatch(collect);
    }
}
