package com.cyheng.controller.admin;

import com.cyheng.model.VO.ResultBean;
import com.cyheng.model.VO.SinglePageParam;
import com.cyheng.service.SinglePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("/api/admin/about")
public class SinglePageController {

    private SinglePageService singlePageService;

    @Autowired
    public SinglePageController(SinglePageService singlePageService) {
        this.singlePageService = singlePageService;
    }


    @GetMapping("/api/admin/about")
    public ResultBean getAboutPage() {
        return ResultBean.ok(singlePageService.getAboutPage());
    }

    @PutMapping("/api/admin/about")
    public ResultBean updateAboutPage(@Valid @RequestBody SinglePageParam param) {
        return ResultBean.ok(singlePageService.updateAboutPage(param));
    }

    @GetMapping("/api/about")
    public ResultBean getPublicAboutPage() {
        return ResultBean.ok(singlePageService.getAboutPage());
    }

}
