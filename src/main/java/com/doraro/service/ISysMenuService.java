package com.doraro.service;

import com.doraro.model.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author doraro
 * @since 2019-03-17
 */
public interface ISysMenuService extends IService<SysMenu> {


    List<SysMenu> getMenuByUserId(Long userId);


}
