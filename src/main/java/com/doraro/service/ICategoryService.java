package com.doraro.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.doraro.model.dto.ArticleDetail;
import com.doraro.model.entity.Category;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author doraro
 * @since 2019-03-01
 */
public interface ICategoryService extends IService<Category> {

    List<ArticleDetail> getArticles(Long id);

    IPage<Category> getCategoryByPage(int pageNum, int pageSize);

    boolean deleteCategoryById(Long id);
}
