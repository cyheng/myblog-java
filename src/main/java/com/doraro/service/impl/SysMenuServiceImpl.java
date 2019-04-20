package com.doraro.service.impl;

import com.doraro.model.entity.SysMenu;
import com.doraro.mapper.SysMenuMapper;
import com.doraro.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doraro.service.ISysResourceService;
import com.doraro.service.ISysRoleService;
import com.doraro.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author doraro
 * @since 2019-03-17
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Autowired
    private ISysUserService userService;


    @Override
    public List<SysMenu> getMenuByUserId(Long userId) {
        if (userService.isAdminRole(userId)) {
            return super.list();
        }
        final List<String> perm = userService.getMenuPermByUserId(userId);
        return super.baseMapper.getMenuByPerm(perm);

    }


}
