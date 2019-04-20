package com.doraro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.doraro.model.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author doraro
 * @since 2019-03-10
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> getRoleByUserId(@Param("userId") Long userId);

    Set<String> getPermByRoleIds(@Param("roleIds") List<Long> roleIds);
}
