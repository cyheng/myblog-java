package com.cyheng.repository;

import com.baomidou.mybatisplus.plugins.Page;
import com.cyheng.ServiceConfig;
import com.cyheng.model.DO.Category;
import com.cyheng.model.VO.ArticleDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ServiceConfig.class})
public class CategoryRepositoryTest {



    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void getArticle() {
        List<ArticleDetail> articles = categoryRepository.getArticles("67ad7d29-3dd5-4401-82ae-a9426b0943fe");
        assertNotNull(articles);
    }


    @Test
    public void findCategoryByName() {
        Category test_name = categoryRepository.findCategoryByName("test name");
        assertNotNull(test_name);
    }


    @Test
    public void getArticleNumByCategoryId() {
        Integer articleNum = categoryRepository.getArticleNum("67d68570-e2d5-4850-a017-d1f76f5a4882");
        assertEquals(6, articleNum.intValue());
    }


    @Test
    public void getByPage() {
        Page<Category> page = new Page<>(0, 5, "update_time");
        List<Category> articleByPage = categoryRepository.getCategoryByPage(page);

        assertEquals(3, articleByPage.size());

    }
}