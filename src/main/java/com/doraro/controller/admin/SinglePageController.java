package com.doraro.controller.admin;

import com.doraro.exception.beans.ApiResponses;
import com.doraro.model.param.SinglePageParam;
import com.doraro.service.SinglePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SinglePageController {

    private SinglePageService singlePageService;

    @Autowired
    public SinglePageController(SinglePageService singlePageService) {
        this.singlePageService = singlePageService;
    }

    @GetMapping("/api/admin/about")
    public ApiResponses getAboutPage() {
        return ApiResponses.ok(singlePageService.getAboutPage());
    }

    @PutMapping("/api/admin/about")
    public ApiResponses updateAboutPage(@Valid @RequestBody SinglePageParam param) {
        return ApiResponses.ok(singlePageService.updateAboutPage(param));
    }

    @GetMapping("/api/about")
    public ApiResponses getPublicAboutPage() {
        return ApiResponses.ok(singlePageService.getAboutPage());
    }

}
