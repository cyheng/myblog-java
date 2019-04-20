package com.doraro.mapper;

import com.doraro.model.entity.SysResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 资源表 Mapper 接口
 * </p>
 *
 * @author doraro
 * @since 2019-03-17
 */
public interface SysResourceMapper extends BaseMapper<SysResource> {

    Set<String> getPermByRoleIds(@Param("ids") List<Long> ids);
}
