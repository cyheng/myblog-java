package com.doraro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.doraro.exception.ApiGlobalException;
import com.doraro.exception.beans.ErrorCodeEnum;
import com.doraro.model.entity.BaseModel;
import com.doraro.model.entity.SysMenu;
import com.doraro.model.entity.SysRole;
import com.doraro.mapper.SysRoleMapper;
import com.doraro.service.ISysMenuService;
import com.doraro.service.ISysRoleMenuService;
import com.doraro.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doraro.service.ISysUserService;
import com.doraro.utils.ApiAssert;
import com.doraro.utils.Constant;
import com.doraro.utils.ShiroUserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author doraro
 * @since 2019-03-10
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
    private final ISysRoleMenuService roleMenuService;
    private final ISysMenuService menuService;

    @Autowired
    public SysRoleServiceImpl(ISysRoleMenuService roleMenuService, ISysMenuService menuService) {
        this.roleMenuService = roleMenuService;
        this.menuService = menuService;
    }

    @Override
    public List<SysRole> getRolesByUserId(Long userId) {
        final List<SysRole> res = super.baseMapper.getRoleByUserId(userId);
        if (res == null) {
            return Collections.emptyList();
        }
        return res;
    }

    @Override
    public List<String> getRoleNamesByUserId(Long userId) {
        final List<SysRole> roles = getRolesByUserId(userId);
        return roles.stream()
                .filter(Objects::nonNull)
                .map(SysRole::getRoleName)
                .filter(s -> !StringUtils.isBlank(s))
                .collect(Collectors.toList());
    }

    /**
     * 超级管理员不能被修改
     * menuId为空时删除所用关联
     *
     * @param id
     * @param param
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateByRoleId(Long id, SysRole param) {
        final SysRole sysRole = super.getById(id);
        if (Constant.ADMIN_NAME.equals(sysRole.getRoleName())) {
            throw new ApiGlobalException(ErrorCodeEnum.FORBIDDEN_EDIT);
        }
        Long userId = ShiroUserUtil.getCurrentUserId();
        param.setUpdateUid(userId);
        checkPerm(param);
        super.update(param, new QueryWrapper<SysRole>().lambda().eq(SysRole::getId, id));
        //更新角色和菜单的关系
        return roleMenuService.saveOrUpdateBatch(id, param.getMenuIdList());
    }


    /**
     * 不能删除当前用户的角色和管理员
     * 不能删除别人创建的角色(但是可以修改
     * 管理员除外,但是管理员还是不能删除管理员
     *
     * @param id
     * @return
     */
    @Override
    public Boolean delByRoleId(Long id) {
        final SysRole role = super.getById(id);
        Long userId = ShiroUserUtil.getCurrentUserId();
        if (Constant.ADMIN_NAME.equals(role.getRoleName())) {
            throw new ApiGlobalException(ErrorCodeEnum.FORBIDDEN_EDIT);
        } else if (isAdminRole(userId)) {
            return super.removeById(id);
        }
        final List<SysRole> roleList = this.getRolesByUserId(userId);
        if (roleList != null) {
            for (SysRole sysRole : roleList) {
                if (id.equals(sysRole.getId())) {
                    throw new ApiGlobalException(ErrorCodeEnum.FORBIDDEN_EDIT);
                }
            }
        }
        return super.removeById(id);
    }

    /**
     * 不能有同名的角色,新增的角色的权限不能超过当前角色的权限
     *
     * @param param
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createRole(SysRole param) {
        final Long userId = ShiroUserUtil.getCurrentUserId();
        final SysRole role = param.setCreateUid(userId).setUpdateUid(userId);
        //前端不允许设置id
        role.setId(null);
        final SysRole one = super.getOne(new QueryWrapper<SysRole>().lambda()
                .eq(SysRole::getRoleName, param.getRoleName()));
        ApiAssert.isNull(ErrorCodeEnum.ROLE_ALREADY_EXISTS, one);
        super.save(role);
        checkPerm(role);
        //更新角色和菜单的关系
        return roleMenuService.saveOrUpdateBatch(role.getId(), role.getMenuIdList());
    }

    @Override
    public Boolean isAdminRole(Long userId) {
        final List<SysRole> roleList = this.getRolesByUserId(userId);
        if (roleList == null) {
            return false;
        }
        for (SysRole sysRole : roleList) {
            if (Constant.ADMIN_NAME.equals(sysRole.getRoleName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isAdminRole(List<SysRole> roles) {
        if (roles == null) {
            return false;
        }
        for (SysRole role : roles) {
            if (Constant.ADMIN_NAME.equals(role.getRoleName())) {
                return true;
            }
        }
        return false;
    }


    private void checkPerm(SysRole role) {
        final List<SysMenu> menus = menuService.getMenuByUserId(role.getCreateUid());
        final List<Long> menuIds = menus.stream().map(BaseModel::getId).collect(Collectors.toList());
        if (!menuIds.containsAll(role.getMenuIdList())) {
            throw new ApiGlobalException("新增角色的权限，已超出你的权限范围");
        }

    }
}
