package com.doraro.service.impl;

import com.doraro.model.entity.SysResource;
import com.doraro.mapper.SysResourceMapper;
import com.doraro.model.entity.SysRole;
import com.doraro.service.ISysResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doraro.utils.Constant;
import com.google.common.base.Strings;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author doraro
 * @since 2019-03-17
 */
@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements ISysResourceService {


    @Override
    public Set<String> getPermByRoleIds(List<Long> ids) {
        return super.baseMapper.getPermByRoleIds(ids);
    }

    @Override
    public Set<String> getPermsByRoles(List<SysRole> userRoles) {
        Objects.requireNonNull(userRoles);
        final List<Long> ids = new ArrayList<>();
        for (SysRole userRole : userRoles) {
            if (Constant.ADMIN_NAME.equals(userRole.getRoleName())) {
                return this.list()
                        .stream().map(SysResource::getPerm)
                        .filter(s -> !Strings.isNullOrEmpty(s))
                        .collect(Collectors.toSet());
            }
            ids.add(userRole.getId());
        }
        final Set<String> res = this.getPermByRoleIds(ids);
        res.remove("");
        return res;
    }
}
