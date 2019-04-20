package com.doraro.mapper;

import com.doraro.model.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author doraro
 * @since 2019-03-17
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {


    List<SysMenu> getMenuByPerm(@Param("perm") List<String> perm);


}
