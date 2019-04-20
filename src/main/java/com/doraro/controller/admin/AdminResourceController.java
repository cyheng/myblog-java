package com.doraro.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doraro.exception.beans.ApiResponses;
import com.doraro.model.dto.PageView;
import com.doraro.model.entity.SysResource;
import com.doraro.model.param.PageParam;
import com.doraro.service.ISysResourceService;
import com.doraro.service.impl.ScanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(description = "服务器资源", tags = "resources")
@RequestMapping("/api/admin/resources")
public class AdminResourceController {
    private final ScanService scanService;
    private ISysResourceService resourceService;

    @Autowired
    public AdminResourceController(ScanService scanService, ISysResourceService resourceService) {
        this.scanService = scanService;
        this.resourceService = resourceService;
    }

    @GetMapping
    @ApiOperation("分页获取资源")
    public ApiResponses getResourceByPage(PageParam pageParam) {
        final Page<SysResource> page = new Page<>(pageParam.getPage(), pageParam.getSize());
        return ApiResponses.ok(new PageView(resourceService.page(page)));
    }

    @PostMapping
    @ApiOperation("重新获取服务器资源")
    public ApiResponses refresh() {
        boolean resources = scanService.scanResource();
        return ApiResponses.ok(resources);
    }
}
