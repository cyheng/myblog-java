package com.doraro.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.doraro.model.enums.SysMenuEnum;
import com.doraro.model.param.ValidateGroups;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author doraro
 * @since 2019-03-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_menu")
@ApiModel(value = "SysMenu对象", description = "菜单表")
public class SysMenu extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "父菜单ID，一级菜单为0")
    @NotNull(message = "父id不能为空", groups = {ValidateGroups.Create.class, ValidateGroups.Update.class})
    private Long parentId;

    @ApiModelProperty(value = "菜单名称")
    @NotNull(message = "名字不能为空", groups = {ValidateGroups.Create.class})
    private String menuName;

    @ApiModelProperty(value = "类型:0:目录,1:菜单,2:按钮")
    @NotNull(message = "类型不能为空", groups = {ValidateGroups.Create.class})
    private SysMenuEnum menuType;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "创建者ID")
    private Long createUid;

    @ApiModelProperty(value = "修改者ID")
    private Long updateUid;

    @ApiModelProperty(value = "排序,值越小越靠前")
    private Integer sortOrder;

    @ApiModelProperty(value = "资源id")
    private Long resId;


    public static final String PARENT_ID = "parent_id";

    public static final String MENU_NAME = "menu_name";

    public static final String MENU_TYPE = "menu_type";

    public static final String ICON = "icon";

    public static final String CREATE_UID = "create_uid";

    public static final String UPDATE_UID = "update_uid";

    public static final String SORT_ORDER = "sort_order";

    public static final String RES_ID = "res_id";

}
