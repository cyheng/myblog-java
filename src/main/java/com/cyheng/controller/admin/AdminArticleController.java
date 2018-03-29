package com.cyheng.controller.admin;

import com.baomidou.mybatisplus.plugins.Page;
import com.cyheng.model.VO.ArticleDetail;
import com.cyheng.model.VO.ArticleParam;
import com.cyheng.model.VO.ResultBean;
import com.cyheng.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by cyheng on 2018/2/23.
 */
@RestController
@Slf4j
@RequestMapping("/api/admin/articles")
public class AdminArticleController {
    private ArticleService articleService;

    @Autowired
    public AdminArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/numbers")
    public ResultBean getArticleNumber() {
        return ResultBean.ok(articleService.countNumbers());
    }

    @GetMapping
    public Page<ArticleDetail> findAll(@RequestParam(value = "page", defaultValue = "0") int pageNum,
                                       @RequestParam(value = "size", defaultValue = "5") int pageSize,
                                       @RequestParam(value = "category", required = false) String categoryId,
                                       @RequestParam(value = "updateTime", required = false) String order) {

        return articleService.findAll(pageNum, pageSize, categoryId, order);
    }

    @GetMapping("/{id}")
    public ArticleDetail getArticle(@PathVariable String id) {
        return articleService.getArticle(id);
    }

    @GetMapping("/getDeletes")
    public Page<ArticleDetail> getAllDeleteArticle(@RequestParam(value = "page", defaultValue = "0") int pageNum,
                                                   @RequestParam(value = "size", defaultValue = "5") int pageSize) {
        return articleService.getAllDeleteArticle(pageNum, pageSize);
    }
    @PostMapping
    public ResponseEntity createArticle(@Valid @RequestBody ArticleParam article) {
        return ResponseEntity.status(HttpStatus.CREATED).body(articleService.saveArticle(article));
    }

    @PutMapping("/{id}")
    public ResultBean updateArticle(@PathVariable String id, @Valid @RequestBody ArticleParam article) {
        return ResultBean.ok(articleService.updateArticle(id, article));
    }

    @DeleteMapping("/{id}")
    public ResultBean deleteArticle(@PathVariable String id) {
        return ResultBean.ok(articleService.deleteArticle(id));
    }
}
