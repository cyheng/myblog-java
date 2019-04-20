package com.doraro.service;

import com.doraro.exception.beans.ErrorCodeEnum;
import com.doraro.model.entity.SinglePage;
import com.doraro.model.param.SinglePageParam;
import com.doraro.mapper.SinglePageMapper;
import com.doraro.utils.ApiAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SinglePageService {
    private SinglePageMapper singlePageMapper;

    @Autowired
    public SinglePageService(SinglePageMapper singlePageMapper) {
        this.singlePageMapper = singlePageMapper;
    }

    public SinglePage getAboutPage() {
        return singlePageMapper.getPageByslug("about");
    }

    public String updateAboutPage(SinglePageParam param) {
//        SinglePage singlePage = singlePageMapper.selectById(param.getId());
//        ApiAssert.isNull(ErrorCodeEnum.SINGLE_PAGE_EXISTS, singlePage);
//        ApiAssert.notEquals(ErrorCodeEnum.NOT_ACCEPTABLE, "about", singlePage.getSlug());

//        singlePage.setContent(param.getContent());
//        singlePageMapper.updateById(singlePage);
//        return singlePage.getId();
        return "";
    }


}
