package com.cyheng.controller.admin;

import com.baomidou.mybatisplus.plugins.Page;
import com.cyheng.exception.CheckException;
import com.cyheng.exception.ResourcesNotFoundException;
import com.cyheng.model.DO.Category;
import com.cyheng.model.VO.CategoryParam;
import com.cyheng.model.VO.ResultBean;
import com.cyheng.repository.CategoryRepository;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by cyheng on 2017/11/26.
 */
@RestController
@Slf4j
public class AdminCategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/api/admin/categories/numbers")
    public ResultBean countCategory() {
        return ResultBean.ok(categoryRepository.countCategory());
    }

    @GetMapping("/api/admin/category/getArticle")
    public ResponseEntity getArticles(String id) {
        if (Strings.isNullOrEmpty(id)) {
            throw new CheckException("参数id不能为空!");
        }
        Category category = categoryRepository.selectById(id);
        if (category == null) {
            throw new ResourcesNotFoundException();
        }
        return ResponseEntity.ok(categoryRepository.getArticles(id));
    }

    @GetMapping("/api/admin/categories")
    public ResponseEntity getAllCategories(@RequestParam(value = "page", defaultValue = "0") int pageNum,
                                           @RequestParam(value = "size", defaultValue = "5") int pageSize) {
        Page<Category> page = new Page<>(pageNum, pageSize, "update_time");
        return ResponseEntity.ok(page.setRecords(categoryRepository.getCategoryByPage(page)));
    }

    @PutMapping("/api/admin/categories")
    public ResultBean updateCategory(@Valid @RequestBody CategoryParam form) {
        Category categoryByName = categoryRepository.findCategoryByName(form.getName());
        if (categoryByName != null) {
            throw new CheckException(String.format("name:%s 已存在", form.getName()));
        }
        Category category = Optional.ofNullable(categoryRepository.selectById(form.getId()))
                .map(item -> {
                    item.setName(form.getName());
                    return item;
                }).orElseThrow(ResourcesNotFoundException::new);
        categoryRepository.updateById(category);
        return ResultBean.ok(category.getId());
    }

    @PostMapping("/api/admin/categories")
    public ResponseEntity createCategory(String name) {
        if (Strings.isNullOrEmpty(name)) {
            throw new CheckException("参数name不能为空!");
        }
        Category category = new Category(name);
        categoryRepository.insert(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }


    @DeleteMapping("/api/admin/categories")
    public ResultBean deleteCategory(String id) {
        if (Strings.isNullOrEmpty(id)) {
            throw new CheckException("参数id不能为空!");
        }
        Category category = categoryRepository.selectById(id);
        if (category == null) {
            throw new ResourcesNotFoundException();
        }
        Integer articleNum = categoryRepository.getArticleNum(id);
        if (articleNum != 0) {
            throw new CheckException("该分类还有文章，无法删除");
        }
        categoryRepository.deleteById(category.getId());

        return ResultBean.ok(category.getId());
    }
}
