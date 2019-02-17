package com.doraro.controller;

import com.doraro.exception.ApiGlobalException;
import com.doraro.exception.beans.ApiResponses;
import com.doraro.model.entity.Article;
import com.doraro.model.entity.Category;
import com.doraro.model.param.ArticleParam;
import com.doraro.model.param.CategoryParam;
import com.doraro.model.param.TestParam;
import com.vip.vjtools.vjkit.id.IdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    @GetMapping("/test")
    public Article test() {
        final ArticleParam articleParam = new ArticleParam();
        articleParam.setTitle("test1");
        articleParam.setContent("this is content");
        articleParam.setCategoryId(IdUtil.fastUUID().toString());
        articleParam.setSummary("this is summary");
        articleParam.setPublished(true);
        articleParam.setShowToc(true);
        articleParam.setAllowComment(true);
        return articleParam.convert(Article.class);
    }

    @GetMapping("/test2")
    public Article test2(@RequestParam Integer id) {

        final ArticleParam articleParam = new ArticleParam();
        articleParam.setTitle("test1");
        articleParam.setContent("this is content");
        articleParam.setCategoryId(id.toString());
        articleParam.setSummary("this is summary");
        articleParam.setPublished(true);
        articleParam.setShowToc(true);
        articleParam.setAllowComment(true);
        return articleParam.convert(Article.class);
    }

    @PostMapping("/test3")
    public Category test3(@Valid @RequestBody CategoryParam param) {

        return param.convert(Category.class);
    }

    @PostMapping("/test4")
    public ApiResponses test4(@Valid @RequestBody TestParam param) {
        logger.info("{}", param);
        return ApiResponses.ok();
    }

    @PostMapping("/test5")
    public ApiResponses test5() {
        return new ApiResponses();
    }
}
