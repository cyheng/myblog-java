package com.doraro.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author doraro
 * @since 2019-03-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_resource")
public class SysResource extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 路径映射
     */
    private String mapping;

    /**
     * 请求方式
     */
    private String method;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 权限标识
     */
    private String perm;


    public static final String RESOURCE_NAME = "resource_name";

    public static final String MAPPING = "mapping";

    public static final String METHOD = "method";

    public static final String AUTH_TYPE = "auth_type";

    public static final String UPDATE_TIME = "update_time";

    public static final String PERM = "perm";

}
