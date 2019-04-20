package com.doraro.generate;

import com.doraro.mapper.SysMenuMapper;
import com.doraro.model.enums.StatusEnum;

import java.time.LocalDateTime;
import java.util.List;

import com.doraro.mapper.ArticleMapper;
import com.doraro.mapper.CategoryMapper;
import com.doraro.mapper.SysUserMapper;
import com.doraro.model.entity.Article;
import com.doraro.model.entity.Category;
import com.doraro.model.entity.SysUser;
import com.doraro.model.enums.ArticleStatusEnum;
import com.doraro.utils.EncryptUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GenerateTestDataTest {
    @Autowired
    ArticleMapper mapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysMenuMapper sysMenuMapper;

    @Test
    public void generDataTest() {
        assert mapper != null;
        for (int i = 0; i < 10; i++) {
            final Article article = new Article();
            for (ArticleStatusEnum statusEnum : ArticleStatusEnum.values()) {
                if (statusEnum.getStatus() == i % 3) {
                    article.setStatus(statusEnum);
                }
            }
            if (i % 2 == 0) {
                article.setAllowComment(false);
                article.setShowToc(true);
            } else {
                article.setAllowComment(true);
                article.setShowToc(false);
            }
            article.setTitle("测试标题" + i);
            article.setContent("测试内容" + i);
            article.setSummary("测试摘要" + i);
            article.setId(Long.valueOf(i + ""));
            mapper.insert(article);
        }
    }

    @Test
    public void genCategory() {
        for (int i = 0; i < 4; i++) {
            final Category category = new Category();
            category.setId(Long.valueOf(i + ""));
            category.setCreateTime(LocalDateTime.now());
            category.setUpdateTime(LocalDateTime.now());
            category.setCategoryName("测试分类" + i);
            categoryMapper.insert(category);
        }
    }

    @Test
    public void genCategoryArticleLink() {
        final List<Article> articles = mapper.selectList(null);
        final List<Category> categories = categoryMapper.selectList(null);
        for (int i = 0; i < articles.size(); i++) {
            final Article article = articles.get(i);
            final Category category = categories.get(i % categories.size());
            categoryMapper.linkCategoryAndArticle(category.getId(), article.getId());
        }
    }

    @Test
    public void genTestAccount() {
        final SysUser sysUser = new SysUser();
        sysUser.setCreateTime(LocalDateTime.now());
        sysUser.setLastLoginTime(LocalDateTime.now());
        sysUser.setUpdateTime(LocalDateTime.now());
        sysUser.setLoginName("test");
        sysUser.setPassword(EncryptUtil.getSha512("123"));
        sysUser.setAvatar("test");
        sysUser.setNickName("test");
        sysUser.setIp("0.0.0.0");
        sysUser.setEmail("123@qq.com");
        sysUser.setStatus(StatusEnum.NORMAL);
        sysUserMapper.insert(sysUser);
    }

    @Test
    public void genTestMenu() {


    }
}
