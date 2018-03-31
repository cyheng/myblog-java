package com.cyheng.repository;


import com.cyheng.model.DO.SinglePage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SinglePageRepositoryTest {

    @Autowired
    private SinglePageRepository singlePageRepository;


    @Test
    public void testSelectBySlug() {
        SinglePage about = singlePageRepository.getPageByslug("about");
        assertThat(about).isNotNull();
        assertThat(about.getIsDelete()).isEqualTo(0);
        assertThat(about).hasFieldOrPropertyWithValue("slug", "about");
        assertThat(about.getContent()).isNotNull();
    }

    @Test
    public void testNeverEmpty() {
        List<SinglePage> singlePages = singlePageRepository.selectList(null);
        assertThat(singlePages).isNotNull();
        assertThat(singlePages.isEmpty()).isFalse();
    }
}