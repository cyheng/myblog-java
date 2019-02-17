package com.doraro.service;

import com.doraro.exception.ApiGlobalException;
import com.doraro.exception.ResourcesNotFoundException;
import com.doraro.exception.beans.ErrorCodeEnum;
import com.doraro.model.entity.SinglePage;
import com.doraro.model.param.SinglePageParam;
import com.doraro.mapper.SinglePageRepository;
import com.doraro.utils.ApiAssert;
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
        ApiAssert.isNull(ErrorCodeEnum.SINGLE_PAGE_EXISTS, singlePage);
        ApiAssert.notEquals(ErrorCodeEnum.NOT_ACCEPTABLE, "about", singlePage.getSlug());

        singlePage.setContent(param.getContent());
        singlePageRepository.updateById(singlePage);
//        return singlePage.getId();
        return "";
    }


}
