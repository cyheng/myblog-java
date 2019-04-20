package com.doraro.service;

import com.doraro.model.entity.CategoryArticle;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author doraro
 * @since 2019-03-04
 */
public interface ICategoryArticleService extends IService<CategoryArticle> {

    boolean saveCategoryArticle(Long id, List<Long> categoryIds);
}
