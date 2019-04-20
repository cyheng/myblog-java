package com.doraro.service;

import com.doraro.model.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author doraro
 * @since 2019-03-10
 */
public interface ISysRoleService extends IService<SysRole> {

    List<SysRole> getRolesByUserId(Long userId);

    List<String> getRoleNamesByUserId(Long userId);

    Boolean updateByRoleId(Long id, SysRole param);


    Boolean delByRoleId(Long id);

    boolean createRole(SysRole param);

    Boolean isAdminRole(Long userId);

    boolean isAdminRole(List<SysRole> roles);


}
