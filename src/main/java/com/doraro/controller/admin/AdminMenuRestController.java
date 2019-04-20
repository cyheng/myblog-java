package com.doraro.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doraro.aop.SysLog;
import com.doraro.exception.ApiGlobalException;
import com.doraro.exception.beans.ApiResponses;
import com.doraro.model.dto.PageView;
import com.doraro.model.entity.SysMenu;
import com.doraro.model.entity.SysRole;
import com.doraro.model.enums.SysMenuEnum;
import com.doraro.model.param.PageParam;
import com.doraro.model.param.ValidateGroups;

import com.doraro.service.ISysMenuService;
import com.doraro.utils.ShiroUserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author doraro
 * @since 2019-02-17
 */
@RestController
@RequestMapping("/api/admin/menu")
@Api(description = "菜单相关接口", tags = "menu")
public class AdminMenuRestController {

    private ISysMenuService menuService;

    @Autowired
    public AdminMenuRestController(ISysMenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * @return
     */
    @GetMapping("/nav")
    @ApiOperation("获取菜单导航")
    public ApiResponses getMenu() {
        final Long userId = ShiroUserUtil.getCurrentUserId();
        List<SysMenu> menus = menuService.getMenuByUserId(userId);
        return ApiResponses.ok(menus);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @RequiresPermissions("sys:menu:info")
    @ApiOperation("获取菜单")
    public ApiResponses getMenu(@PathVariable Long id) {
        return ApiResponses.ok(menuService.getById(id));
    }

    @GetMapping
    @RequiresPermissions("sys:menu:view")
    @ApiOperation("分页获取菜单")
    public ApiResponses getMenuByPage(PageParam pageParam) {
        final Page<SysMenu> page = new Page<>(pageParam.getPage(), pageParam.getSize());
        final IPage<SysMenu> menuIPage = menuService.page(page);
        return ApiResponses.ok(new PageView(menuIPage));
    }

    /**
     * @param param
     * @return
     */
    @PostMapping
    @RequiresPermissions("sys:menu:create")
    @SysLog
    @ApiOperation("创建菜单")
    public ApiResponses createMenu(@Validated(ValidateGroups.Create.class) @RequestBody SysMenu param) {
        final Long userId = ShiroUserUtil.getCurrentUserId();
        param.setCreateUid(userId)
                .setUpdateUid(userId);
        checkMenuType(param);
        return ApiResponses.ok(menuService.save(param));
    }

    /**
     * 目录-菜单-按钮
     *
     * @param param
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    @RequiresPermissions("sys:menu:update")
    @SysLog
    @ApiOperation("更新菜单")
    public ApiResponses updateMenu(@Validated(ValidateGroups.Update.class) @RequestBody SysMenu param, @PathVariable Long id) {
        final Long userId = ShiroUserUtil.getCurrentUserId();
        param.setUpdateUid(userId);
        param.setId(id);
        checkMenuType(param);
        return ApiResponses.ok(menuService.updateById(param));
    }


    /**
     * 需要删除子菜单和按钮才能删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @RequiresPermissions("sys:menu:del")
    @SysLog
    @ApiOperation("删除菜单")
    public ApiResponses delMenu(@PathVariable Long id) {
        final List<SysMenu> children = menuService.lambdaQuery().eq(SysMenu::getParentId, id).list();
        if (children != null && !children.isEmpty()) {
            return ApiResponses.failure("请先删除子菜单或按钮");
        }
        return ApiResponses.ok(menuService.removeById(id));
    }


    private void checkMenuType(SysMenu param) {
        final Long parentId = param.getParentId();
        SysMenuEnum parentType = SysMenuEnum.DIR;
        if (parentId != 0) {
            final SysMenu parent = menuService.lambdaQuery().eq(SysMenu::getParentId, parentId).one();
            parentType = parent.getMenuType();
        }
        //目录、菜单
        if (param.getMenuType().equals(SysMenuEnum.DIR) || param.getMenuType().equals(SysMenuEnum.MENU)) {
            if (!parentType.equals(SysMenuEnum.DIR)) {
                throw new ApiGlobalException("上级菜单只能为目录类型");
            }
            return;
        }
        //按钮
        if (param.getMenuType().equals(SysMenuEnum.BUTTON)) {
            if (parentType != SysMenuEnum.MENU) {
                throw new ApiGlobalException("上级菜单只能为菜单类型");
            }
        }

    }
}

