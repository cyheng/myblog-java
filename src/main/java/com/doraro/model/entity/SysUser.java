package com.doraro.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import com.doraro.model.enums.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author doraro
 * @since 2019-03-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
public class SysUser extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 上次登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 用户登陆账号
     */
    private String loginName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户名
     */
    private String nickName;

    /**
     * 上次登录的ip地址
     */
    private String ip;

    /**
     * 注册邮箱
     */
    private String email;

    /**
     * 账号状态
     */
    private StatusEnum status;

    public static final String CREATE_TIME = "create_time";

    public static final String LAST_LOGIN_TIME = "last_login_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String LOGIN_NAME = "login_name";

    public static final String PASSWORD = "password";

    public static final String AVATAR = "avatar";

    public static final String NICK_NAME = "nick_name";

    public static final String IP = "ip";

    public static final String EMAIL = "email";

}
