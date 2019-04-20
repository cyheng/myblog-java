package com.doraro.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.doraro.model.entity.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.List;

import com.doraro.model.param.ValidateGroups;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotBlank;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author doraro
 * @since 2019-03-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role")
@ApiModel(value = "SysRole对象", description = "角色表")
public class SysRole extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色名称")
    @NotBlank(message = "角色名不能为空", groups = ValidateGroups.Create.class)
    private String roleName;

    @ApiModelProperty(value = "创建者ID")
    private Long createUid;

    @ApiModelProperty(value = "修改者ID")
    private Long updateUid;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "备注")
    private String remark;
    @TableField(exist = false)
    @ApiModelProperty(value = "菜单id列表")
    private List<Long> menuIdList;

    public static final String ROLE_NAME = "role_name";

    public static final String CREATE_UID = "create_uid";

    public static final String UPDATE_UID = "update_uid";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String REMARK = "remark";

}

