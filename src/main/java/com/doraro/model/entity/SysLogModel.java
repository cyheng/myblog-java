package com.doraro.model.entity;

import com.doraro.model.entity.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author doraro
 * @since 2019-03-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_log")
@ApiModel(value = "SysLog对象", description = "")
public class SysLogModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private Long uid;

    @ApiModelProperty(value = "用户操作")
    private String operation;

    @ApiModelProperty(value = "请求方法")
    private String method;

    @ApiModelProperty(value = "请求参数")
    private String params;

    @ApiModelProperty(value = "执行时间")
    private Long time;

    @ApiModelProperty(value = "用户ip")
    private String ip;

    private LocalDateTime createTime;


    public static final String OPERATION = "operation";

    public static final String METHOD = "method";

    public static final String PARAMS = "params";

    public static final String TIME = "time";

    public static final String IP = "ip";

    public static final String CREATE_TIME = "create_time";

}
