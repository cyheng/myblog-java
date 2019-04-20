package com.doraro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.doraro.exception.beans.ErrorCodeEnum;
import com.doraro.model.entity.SysRole;
import com.doraro.model.entity.SysUser;
import com.doraro.mapper.SysUserMapper;
import com.doraro.model.param.UserDetail;
import com.doraro.model.param.SysUserParam;
import com.doraro.service.ISysResourceService;
import com.doraro.service.ISysRoleService;
import com.doraro.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doraro.utils.ApiAssert;
import com.doraro.utils.EncryptUtil;
import com.doraro.utils.HttpContextUtils;
import com.doraro.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author doraro
 * @since 2019-03-10
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Autowired
    private ISysRoleService roleService;

    /**
     * TODO:
     *
     * @param uploadfile
     * @return
     */

    @Override
    public Boolean setAvatar(MultipartFile uploadfile) {
        return null;
    }

    @Override
    public UserDetail getUserInfo(String token) {
        final Long userId = JwtUtil.getUserId(token);
        final SysUser user = super.getById(userId);
        final UserDetail convert = user.convert(UserDetail.class);
        convert.setName(user.getNickName());
        final List<String> names = roleService.getRoleNamesByUserId(userId);
        convert.setRoles(names);
        return convert;
    }

    @Override
    public SysUser login(SysUserParam user, HttpServletRequest request) {
        final SysUser sysUser = this.getUserByName(user.getUsername());
        ApiAssert.notNull(ErrorCodeEnum.USERNAME_OR_PASSWORD_IS_WRONG, sysUser);
        String encode = EncryptUtil.getSha512(user.getPassword());
        ApiAssert.equals(ErrorCodeEnum.USERNAME_OR_PASSWORD_IS_WRONG, sysUser.getPassword(), encode);
        //更新last login time,ip
        super.update(sysUser, new UpdateWrapper<SysUser>()
                .lambda()
                .set(SysUser::getLastLoginTime, LocalDateTime.now())
                .set(SysUser::getIp, HttpContextUtils.getIpAddr(request))
        );
        return sysUser;
    }

    @Override
    public SysUser getUserByName(String username) {
        return super.getOne(new QueryWrapper<SysUser>().lambda()
                .eq(SysUser::getLoginName, username));
    }


    @Override
    public List<String> getMenuPermByUserId(Long userId) {
        return super.baseMapper.getMenuPermByUserId(userId);
    }

    @Override
    public boolean isAdminRole(Long userId) {
        return roleService.isAdminRole(userId);
    }


}
