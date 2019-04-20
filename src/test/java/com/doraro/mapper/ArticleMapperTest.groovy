package com.doraro.mapper

import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.doraro.config.MybatisPlusConfig
import com.doraro.model.entity.Article
import com.doraro.model.entity.Category
import com.doraro.model.enums.ArticleStatusEnum
import org.apache.ibatis.session.SqlSession
import org.apache.ibatis.session.SqlSessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional
import spock.lang.Shared
import spock.lang.Specification

import java.time.LocalDateTime

@ImportAutoConfiguration
@Import(MybatisPlusConfig)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional(rollbackFor = Exception)
@Rollback
class ArticleMapperTest extends Specification {
    @Autowired
    ArticleMapper mapper
    @Autowired
    CategoryMapper categoryMapper

    def testGetUnDeleteArticleByPageWithId() {
        given:
        def page = new Page<Article>(0, 5)
        when:
        def res = mapper.getUnDeleteArticleByPage(page, null)
        then:
        res != null
        res.records != null
        res.records.size() == 5

    }

    def testGetAllDeleteArticle() {
        given:
        def page = new Page<Article>(0, 5)
        when:
        def res = mapper.getAllDeleteArticle(page)
        then:
        res != null
        res.records != null
        res.records.size() == 5


    }

    def testGetPublishedArticleByPage() {
        given:
        def page = new Page<Article>(0, 5)
        when:
        def res = mapper.getPublishedArticleByPage(page, null)
        then:
        res != null
        res.records != null
        res.records.size() == 5

    }
}
