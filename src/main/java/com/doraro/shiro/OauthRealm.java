package com.doraro.shiro;

import com.doraro.model.entity.SysRole;
import com.doraro.model.entity.SysUser;
import com.doraro.model.enums.StatusEnum;
import com.doraro.service.ISysResourceService;
import com.doraro.service.ISysRoleService;
import com.doraro.service.ISysUserService;
import com.doraro.utils.JwtUtil;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class OauthRealm extends AuthorizingRealm {
    private final ISysUserService userService;
    private final ISysRoleService roleService;
    private final ISysResourceService resourceService;

    @Autowired
    public OauthRealm(ISysUserService userService, ISysRoleService roleService, ISysResourceService resourceService) {
        this.userService = userService;
        this.roleService = roleService;
        this.resourceService = resourceService;
    }

    /**
     * Require等注解才会用到
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Long userId = (Long) principals.getPrimaryPrincipal();
        final SysUser sysUser = userService.getById(userId);
        if (sysUser == null) {
            throw new UnknownAccountException("无此账号");
        }
        if (!StatusEnum.NORMAL.equals(sysUser.getStatus())) {
            throw new DisabledAccountException("账号被禁用");
        }
        final SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        final List<SysRole> userRoles = roleService.getRolesByUserId(userId);
        info.addRoles(userRoles.stream()
                .filter(Objects::nonNull)
                .map(SysRole::getRoleName)
                .filter(s -> !StringUtils.isBlank(s))
                .collect(Collectors.toSet()));
        info.addStringPermissions(resourceService.getPermsByRoles(userRoles));
        return info;
    }

    /***
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String tokenCredentials = (String) token.getCredentials();
        final Long userId = JwtUtil.getUserId(tokenCredentials);

        if (!JwtUtil.verify(tokenCredentials, userId)) {
            throw new UnsupportedTokenException("token不存在或者已经失效");
        }
        return new SimpleAuthenticationInfo(userId, tokenCredentials, getName());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof Oauth2Token;
    }
}
