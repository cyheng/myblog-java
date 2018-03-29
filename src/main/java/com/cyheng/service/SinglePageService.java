package com.cyheng.service;

import com.cyheng.exception.ResourcesNotFoundException;
import com.cyheng.model.DO.SinglePage;
import com.cyheng.model.VO.SinglePageParam;
import com.cyheng.repository.SinglePageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SinglePageService {
    private SinglePageRepository singlePageRepository;

    @Autowired
    public SinglePageService(SinglePageRepository singlePageRepository) {
        this.singlePageRepository = singlePageRepository;
    }

    public SinglePage getAboutPage() {
        return singlePageRepository.getPageByslug("about");
    }

    public String updateAboutPage(SinglePageParam param) {
        SinglePage singlePage = singlePageRepository.selectById(param.getId());
        if (singlePage == null || !"about".equals(singlePage.getSlug())) {
            throw new ResourcesNotFoundException();
        }
        singlePage.setContent(param.getContent());
        singlePageRepository.updateById(singlePage);
        return singlePage.getId();
    }


}
