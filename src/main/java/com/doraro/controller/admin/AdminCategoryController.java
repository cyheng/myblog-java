package com.doraro.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doraro.exception.beans.ApiResponses;
import com.doraro.exception.beans.ErrorCodeEnum;
import com.doraro.model.dto.PageView;
import com.doraro.model.entity.Category;
import com.doraro.model.param.CategoryParam;
import com.doraro.mapper.CategoryMapper;
import com.doraro.model.param.PageParam;
import com.doraro.model.param.ValidateGroups;
import com.doraro.service.ICategoryService;
import com.doraro.utils.ApiAssert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * Created by cyheng on 2017/11/26.
 */
@RestController
@Api(tags = {"AdminCategory"}, description = "后台标签管理相关接口")
public class AdminCategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/api/admin/categories/numbers")
    @ApiOperation("获取标签数量")
    public ApiResponses countCategory() {
        return ApiResponses.ok(
                categoryService.count()
        );
    }

    @GetMapping("/api/admin/category/getArticle")
    @ApiOperation("获取标签id下的所有文章")
    public ApiResponses getArticles(@RequestParam Long id) {
        return ApiResponses.ok(categoryService.getArticles(id));
    }

    @GetMapping("/api/admin/categories")
    @ApiOperation("分页获取标签")
    public ApiResponses getAllCategories(PageParam pageParam) {
        return ApiResponses.ok(new PageView(categoryService.getCategoryByPage(pageParam.getPage(), pageParam.getSize())));
    }

    @PutMapping("/api/admin/categories")
    @ApiOperation("修改标签")
    @Transactional(rollbackFor = Exception.class)
    public ApiResponses updateCategory(@Validated(ValidateGroups.Update.class) @RequestBody CategoryParam form) {
        final Category convert = form.convert(Category.class);
        return ApiResponses.ok(categoryService.updateById(convert));
    }

    @PostMapping("/api/admin/categories")
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("创建标签")
    public ApiResponses createCategory(@Validated(ValidateGroups.Create.class) @RequestBody CategoryParam form) {
        Category category = new Category().setCategoryName(form.getCategoryName());
        return ApiResponses.ok(categoryService.save(category));
    }


    @DeleteMapping("/api/admin/categories")
    @ApiOperation("删除标签")
    public ApiResponses deleteCategory(@RequestParam Long id) {
        return ApiResponses.ok(categoryService.deleteCategoryById(id));
    }


}
