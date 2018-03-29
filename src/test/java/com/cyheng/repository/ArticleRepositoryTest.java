package com.cyheng.repository;

import com.baomidou.mybatisplus.plugins.Page;
import com.cyheng.ServiceConfig;
import com.cyheng.model.VO.ArchivesArticle;
import com.cyheng.model.VO.ArticleDetail;
import com.cyheng.model.VO.ArticleListData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ServiceConfig.class})
public class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;


    @Test
    public void getPublicArticleByPage() {
        Page<ArticleDetail> page = new Page<>(0, 5, "update_time");
        List<ArticleListData> data = articleRepository.getPushishedArticleByPage(page, null);
        assertThat(data.size()).isEqualTo(5);
        assertThat(page.getRecords()).doesNotContainNull();
    }

    @Test
    public void findArticleById() {
        ArticleDetail byId = articleRepository.findById("3efbaf2878ca4fa8a0b582e3b5b03a98");
        assertThat(byId).isNotNull();
        assertThat(byId).hasNoNullFieldsOrProperties();
    }

    @Test
    public void getArticleByPage() {
        Page<ArticleDetail> page = new Page<>(0, 5, "update_time");
        List<ArticleDetail> articleByPage = articleRepository.getArticleByPage(page, null);
        assertThat(articleByPage.size()).isEqualTo(5);
    }

    @Test
    public void getAllDeleteArticle() {
        Page<ArticleDetail> page = new Page<>(0, 5, "update_time");
        List<ArticleDetail> allDeleteArticle = articleRepository.getAllDeleteArticle(page);
        assertThat(allDeleteArticle.size()).isEqualTo(4);
    }

    @Test
    public void getArchives() {
        Page<ArchivesArticle> page = new Page<>(0, Integer.MAX_VALUE, "create_time");
        List<ArchivesArticle> archives = articleRepository.getArchives(page);
        Map<Integer, List<ArchivesArticle>> collect = archives.stream().collect(groupingBy((item) -> getYear(item.getCreateTime())));
        assertThat(collect).containsKeys(2018);

    }


    @Test
    public void getPublishedArticleById() {
        ArticleDetail wrong_id = articleRepository.getPublishedArticleById("wrong id");
        ArticleDetail draft_article = articleRepository.getPublishedArticleById("eccb5519-5e4c-482e-a2b0-5ecc2d73b032");
        ArticleDetail publish_article = articleRepository.getPublishedArticleById("a8fc7a69fba44ae49389d525b33130e0");
        ArticleDetail delete_article = articleRepository.getPublishedArticleById("5a6b2b8541b244139fe79a2115342d6b");
        assertThat(wrong_id).isNull();
        assertThat(draft_article).isNull();
        assertThat(publish_article).isNotNull();
        assertThat(delete_article).isNull();
    }


    @Test
    public void countArticles() {
        Integer count = articleRepository.countArticles();
        assertThat(count).isEqualTo(9);
    }

    private int getYear(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate().getYear();
    }

}