package com.doraro.model.entity;

import com.doraro.model.entity.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author doraro
 * @since 2019-02-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("comment_user")
public class CommentUser extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 首次登录时间
     */
    private LocalDateTime createTime;

    /**
     * 上次登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 头像路径
     */
    private String avatar;

    /**
     * 用户名
     */
    private String nickname;


    public static final String CREATE_TIME = "create_time";

    public static final String LAST_LOGIN_TIME = "last_login_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String AVATAR = "avatar";

    public static final String NICKNAME = "nickname";

}
