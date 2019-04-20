package com.doraro.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doraro.exception.beans.ApiResponses;
import com.doraro.model.dto.ArchivesArticleView;
import com.doraro.model.dto.ArticleDetail;
import com.doraro.model.dto.PageView;
import com.doraro.model.param.ArticleListData;
import com.doraro.model.param.PageParam;
import com.doraro.service.impl.ArticleServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by cyheng on 2017/10/29.
 */
@RestController
@Api(tags = "Article", description = "前台文章接口")
public class ArticleController {
    @Autowired
    private ArticleServiceImpl articleServiceImpl;


    @ApiOperation(value = "获取文章列表", notes = "分页获取文章列表")
    @GetMapping("/api/articles")
    public ApiResponses findAll(PageParam pageParam, @RequestParam(value = "category", required = false) Long categoryId) {
        final IPage<ArticleListData> page = articleServiceImpl.getPublishedArticleByPage(pageParam.getPage(), pageParam.getSize(), categoryId);
        return ApiResponses.ok(new PageView(page));
    }


    @ApiOperation(value = "获得单个文章", notes = "获得单个文章")
    @GetMapping(value = "/api/articles/{id}")
    public ApiResponses findArticleById(@PathVariable Long id) {
        return ApiResponses.ok(articleServiceImpl.getPublishedArticleById(id));
    }

    @ApiOperation("以年份分类文章")
    @GetMapping(value = "/api/archives")
    public ApiResponses getArchives() {
        return ApiResponses.ok(articleServiceImpl.getArchives());
    }
}
