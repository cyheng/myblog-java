package com.doraro.service;

import com.doraro.model.entity.SysResource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.doraro.model.entity.SysRole;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author doraro
 * @since 2019-03-17
 */
public interface ISysResourceService extends IService<SysResource> {

    Set<String> getPermByRoleIds(List<Long> ids);

    Set<String> getPermsByRoles(List<SysRole> userRoles);
}
