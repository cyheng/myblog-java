package com.doraro.model.entity;

import com.doraro.model.entity.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户点赞表
 * </p>
 *
 * @author doraro
 * @since 2019-02-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user_like")
public class UserLike extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 被点赞的用户id
     */
    private Long likedUserId;

    /**
     * 点赞的用户id
     */
    private Long userId;

    /**
     * 点赞状态，0取消，1点赞
     */
    private Boolean status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


    public static final String LIKED_USER_ID = "liked_user_id";

    public static final String USER_ID = "user_id";

    public static final String STATUS = "code";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

}
