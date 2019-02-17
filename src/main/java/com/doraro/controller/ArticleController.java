package com.doraro.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doraro.model.param.ArticleDetail;
import com.doraro.model.param.ArticleListData;
import com.doraro.service.ArticleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by cyheng on 2017/10/29.
 */
@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;


    @ApiOperation(value = "获取文章列表", notes = "分页获取文章")
    @GetMapping
    public Page<ArticleListData> findAll(@RequestParam(value = "page", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "size", defaultValue = "5") int pageSize, @RequestParam(value = "category", required = false) String categoryId) {

        return articleService.getPublishedArticleByPage(pageNum, pageSize, categoryId);
    }


    @ApiOperation(value = "获得单个文章", notes = "获得单个文章")
    @GetMapping(value = "/{id}")
    public ArticleDetail findArticleById(@PathVariable String id) {

        return articleService.getPublishedArticleById(id);
    }


}
