package com.cyheng.model.Mapper;

import com.cyheng.model.DO.Article;
import com.cyheng.model.DO.Category;
import com.cyheng.model.VO.ArticleDetail;
import com.cyheng.model.VO.ArticleParam;
import com.cyheng.model.VO.CategoryView;
import org.mapstruct.*;

/**
 * Created by cyheng on 2018/2/28.
 */
@Mapper(componentModel = "spring")
public interface ArticleMapper {

    ArticleDetail toDetail(Article article);

    CategoryView toCategory(Category category);


    @Mappings({
            @Mapping(target = "categoryId", ignore = true),
            @Mapping(source = "published", target = "status", qualifiedByName = "draft")
    })
    void paramToEnity(ArticleParam articleParam, @MappingTarget Article article);

    @Named("draft")
    default String draft(Boolean published) {
        if (published == null) {
            return null;
        }
        return published ? "发布" : "草稿";
    }

}
