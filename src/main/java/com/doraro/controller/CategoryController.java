package com.doraro.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doraro.exception.beans.ErrorCodeEnum;
import com.doraro.model.dto.PageView;
import com.doraro.model.entity.Category;
import com.doraro.mapper.CategoryMapper;
import com.doraro.model.param.PageParam;
import com.doraro.service.ICategoryService;
import com.doraro.utils.ApiAssert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created by cyheng on 2017/10/29.
 */
@RestController
@RequestMapping("/api/category")
@Api(tags = "Category", description = "前台分类接口")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @ApiOperation(value = "获取单个分类", notes = "根据id获取单个分类")
    @GetMapping(value = "/{id}")
    public ResponseEntity findCategoryById(@PathVariable Long id) {
        final Category category = categoryService.getById(id);
        ApiAssert.notNull(ErrorCodeEnum.CATEGORY_NOT_FOUND, category);
        return ResponseEntity.ok(category);
    }

    @ApiOperation(value = "获取分类列表", notes = "获取所有分类列表")
    @GetMapping
    public ResponseEntity getAllCategory(PageParam pageParam) {
        Page<Category> page = new Page<>(pageParam.getPage(), pageParam.getSize());
        page.setDesc("update_time");
        final IPage<Category> iPage = categoryService.page(page);
        return ResponseEntity.ok(new PageView(iPage));

    }
}
