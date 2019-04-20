package com.doraro.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.doraro.exception.beans.ApiResponses;
import com.doraro.mapper.CategoryArticleMapper;
import com.doraro.mapper.CategoryMapper;
import com.doraro.model.dto.ArticleDetail;
import com.doraro.model.dto.PageView;
import com.doraro.model.entity.CategoryArticle;
import com.doraro.model.param.ArticleParam;
import com.doraro.model.param.PageParam;
import com.doraro.service.ICategoryArticleService;
import com.doraro.service.impl.ArticleServiceImpl;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * Created by cyheng on 2018/2/23.
 */
@RestController
@RequestMapping("/api/admin/articles")
@Api(tags = {"AdminArticle"}, description = "后台文章管理相关接口")
public class AdminArticleController {
    private ArticleServiceImpl articleServiceImpl;
    private ICategoryArticleService categoryArticleService;

    @Autowired
    public AdminArticleController(ArticleServiceImpl articleServiceImpl, ICategoryArticleService categoryArticleService) {
        this.articleServiceImpl = articleServiceImpl;

        this.categoryArticleService = categoryArticleService;
    }

    @GetMapping("/numbers")
    @ApiOperation("获取未删除的文章数量")
    @RequiresPermissions("sys:article:view")
    public ApiResponses getArticleNumber() {
        return ApiResponses.ok(articleServiceImpl.countNumbers());
    }

    @GetMapping
    @ApiOperation("分页获取未删除的文章")
    @RequiresPermissions("sys:article:view")
    public ApiResponses findAllUnDelete(PageParam page, Long categoryId, Boolean desc) {
        final IPage<ArticleDetail> data = articleServiceImpl.findAllUnDelete(page.getPage(), page.getSize(), categoryId, desc);
        return ApiResponses.ok(new PageView(data));
    }

    @ApiOperation(value = "获取文章详细信息", notes = "根据url的id来获取文章详细信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "文章ID", required = true, dataType = "Long", example = "1")
    @GetMapping("/{id}")
    @RequiresPermissions("sys:article:view")
    public ApiResponses getArticle(@PathVariable Long id) {
        return ApiResponses.ok(articleServiceImpl.getArticle(id));

    }

    @ApiOperation("分页获取正删除的文章")
    @GetMapping("/getDeletes")
    @RequiresPermissions("sys:article:view")
    public ApiResponses getAllDeleteArticle(PageParam pageParam) {
        final IPage<ArticleDetail> page = articleServiceImpl.getAllDeleteArticle(pageParam.getPage(), pageParam.getSize());
        return ApiResponses.ok(new PageView(page));
    }

    @ApiOperation(value = "创建文章")
    @PostMapping
    public ApiResponses createArticle(@Valid @RequestBody ArticleParam article) {
        return ApiResponses.ok(articleServiceImpl.saveArticle(article));
    }

    @ApiOperation(value = "更新文章")
    @ApiImplicitParam(paramType = "path", name = "id", value = "文章ID", required = true, dataType = "Long", example = "1")
    @PutMapping("/{id}")
    @RequiresPermissions("sys:article:update")
    public ApiResponses updateArticle(@PathVariable Long id, @Valid @RequestBody ArticleParam article) {
        return ApiResponses.ok(articleServiceImpl.updateArticle(id, article));
    }

    @ApiOperation(value = "删除文章")
    @ApiImplicitParam(paramType = "path", name = "id", value = "文章ID", required = true, dataType = "Long", example = "1")
    @DeleteMapping("/{id}")
    @RequiresPermissions("sys:article:del")
    public ApiResponses deleteArticle(@PathVariable Long id) {
        return ApiResponses.ok(articleServiceImpl.softDeleteArticle(id));
    }

    @ApiOperation("修改文章标签")
    @PutMapping("/{articleId}/category")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "articleId", value = "文章ID", required = true, dataType = "Long", example = "1"),
            @ApiImplicitParam(paramType = "body", name = "categoryIds", value = "标签ID(如:[1,2])", required = true, dataType = "Array[Long]",
                    examples = @Example(@ExampleProperty(value = "[1,2]"))
            )
    })
    @RequiresPermissions("sys:article:update")
    public ApiResponses categoryies(@PathVariable Long articleId, @RequestBody @NotEmpty(message = "标签ID不能为空") List<Long> categoryIds) {
        return ApiResponses.ok(categoryArticleService.saveCategoryArticle(articleId, categoryIds));
    }

}
