package com.doraro.mapper;

import com.doraro.model.entity.SysMenu;
import com.doraro.model.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author doraro
 * @since 2019-03-10
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {


    List<String> getMenuPermByUserId(@Param("id") Long id);
}
