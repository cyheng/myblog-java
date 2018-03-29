package com.cyheng.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.cyheng.exception.ResourcesNotFoundException;
import com.cyheng.model.DO.Category;
import com.cyheng.repository.CategoryRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created by cyheng on 2017/10/29.
 */
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @ApiOperation(value = "获取单个分类", notes = "根据id获取单个分类")
    @GetMapping(value = "/{id}")
    public ResponseEntity findCategoryById(@PathVariable String id) {
        Category category = categoryRepository.selectById(id);
        if (category == null) {
            throw new ResourcesNotFoundException("Not Found!");
        }
        return ResponseEntity.ok(category);
    }

    @ApiOperation(value = "获取分类列表", notes = "获取所有分类列表")
    @GetMapping
    public ResponseEntity getAllCategory(@RequestParam(value = "page", defaultValue = "0") int pageNum,
                                         @RequestParam(value = "size", defaultValue = "5") int pageSize) {
        Page<Category> page = new Page<>(pageNum, pageSize, "update_time");
        return ResponseEntity.ok(page.setRecords(categoryRepository.getCategoryByPage(page)));
    }
}
