package com.doraro.controller;

import com.doraro.exception.beans.ApiResponses;
import com.doraro.model.entity.Article;
import com.doraro.model.entity.Category;
import com.doraro.model.enums.ArticleStatusEnum;
import com.doraro.model.enums.StatusEnum;
import com.doraro.model.param.ArticleParam;
import com.doraro.model.param.CategoryParam;
import com.doraro.model.param.Test4Param;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/api/admin/test")
    @ApiOperation("test")
    @RequiresRoles("a")
    public Article test() {
        final ArticleParam articleParam = new ArticleParam();
        articleParam.setTitle("test1");
        articleParam.setContent("this is content");
        articleParam.setSummary("this is summary");
        articleParam.setPublished(true);
        articleParam.setShowToc(true);
        articleParam.setAllowComment(true);
        return articleParam.convert(Article.class);
    }

    @GetMapping("/test2")
    public Article test2(@RequestParam Long id) {
        final ArticleParam articleParam = new ArticleParam();
        articleParam.setTitle("test1");
        articleParam.setContent("this is content");
        articleParam.setSummary("this is summary");
        articleParam.setPublished(true);
        articleParam.setShowToc(true);
        articleParam.setAllowComment(true);
        final Article convert = articleParam.convert(Article.class);
        convert.setId(id);
        return convert;
    }

    @PostMapping("/test3")
    public Category test3(@Valid @RequestBody CategoryParam param) {

        return param.convert(Category.class);
    }


    @GetMapping("/test5")
    @ApiOperation("enum序列化测试")
    public ApiResponses test5() {
        return ApiResponses.ok();
    }
}
