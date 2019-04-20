package com.doraro.model.param;

import com.doraro.model.entity.Article;
import com.vip.vjtools.vjkit.id.IdUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArticleParamTest {
    private static final Logger logger = LoggerFactory.getLogger(ArticleParamTest.class);

    @Test
    public void testVOtoDO() {
        final ArticleParam articleParam = new ArticleParam();
        articleParam.setTitle("test1");
        articleParam.setContent("this is content");
        articleParam.setSummary("this is summary");
        articleParam.setPublished(true);
        articleParam.setShowToc(true);
        articleParam.setAllowComment(true);
        final Article map = articleParam.convert(Article.class);
        logger.info("{}", map);

    }
}