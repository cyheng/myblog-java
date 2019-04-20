package com.doraro.service;

import com.doraro.model.entity.SysRole;
import com.doraro.model.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.doraro.model.param.UserDetail;
import com.doraro.model.param.SysUserParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author doraro
 * @since 2019-03-10
 */
public interface ISysUserService extends IService<SysUser> {

    Boolean setAvatar(MultipartFile uploadfile);

    UserDetail getUserInfo(String token);

    SysUser login(SysUserParam user, HttpServletRequest request);

    SysUser getUserByName(String username);


    List<String> getMenuPermByUserId(Long userId);

    boolean isAdminRole(Long userId);
}
