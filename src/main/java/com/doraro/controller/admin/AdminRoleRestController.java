package com.doraro.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doraro.aop.SysLog;
import com.doraro.exception.beans.ApiResponses;
import com.doraro.model.dto.PageView;
import com.doraro.model.entity.SysRole;
import com.doraro.model.entity.SysRoleMenu;
import com.doraro.model.param.PageParam;
import com.doraro.model.param.ValidateGroups;
import com.doraro.service.ISysRoleMenuService;
import com.doraro.service.ISysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author doraro
 * @since 2019-02-17
 */
@RestController
@RequestMapping("/api/admin/role")
@Api(description = "角色管理相关接口", tags = "role")
public class AdminRoleRestController {
    private final ISysRoleService roleService;
    private final ISysRoleMenuService roleMenuService;

    @Autowired
    public AdminRoleRestController(ISysRoleService resourceService, ISysRoleMenuService roleMenuService) {
        this.roleService = resourceService;
        this.roleMenuService = roleMenuService;
    }

    @PostMapping
    @ApiOperation("修改角色信息")
    @SysLog
    @RequiresPermissions("sys:role:create")
    public ApiResponses createRole(@RequestBody @Validated(ValidateGroups.Create.class) SysRole param) {

        return ApiResponses.ok(roleService.createRole(param));
    }

    @PutMapping("/{id}")
    @ApiOperation("修改角色信息")
    @SysLog
    @RequiresPermissions("sys:role:update")
    public ApiResponses updateRole(@PathVariable Long id, @RequestBody SysRole param) {
        return ApiResponses.ok(roleService.updateByRoleId(id, param));
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("获取单个角色信息")
    @RequiresPermissions("sys:role:get")
    public ApiResponses getRoleById(@PathVariable Long id) {
        //查询角色对应的菜单
        final LambdaQueryWrapper<SysRoleMenu> eq = new QueryWrapper<SysRoleMenu>().lambda().eq(SysRoleMenu::getRoleId, id);
        final List<Long> menuIds = roleMenuService.list(eq).stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
        final SysRole role = roleService.getById(id);
        role.setMenuIdList(menuIds);
        return ApiResponses.ok(role);
    }

    /**
     * @param pageParam
     * @return
     */
    @GetMapping
    @ApiOperation("分页获取角色")
    @RequiresPermissions("sys:role:get")
    public ApiResponses getRoleByPage(PageParam pageParam) {
        IPage<SysRole> page = new Page<>(pageParam.getPage(), pageParam.getSize());
        return ApiResponses.ok(new PageView(roleService.page(page)));
    }

    @DeleteMapping("/{id}")
    @SysLog
    @RequiresPermissions("sys:role:del")
    @ApiOperation("根据roleId删除角色")
    public ApiResponses delRoleById(@PathVariable Long id) {
        return ApiResponses.ok(roleService.delByRoleId(id));
    }


}

