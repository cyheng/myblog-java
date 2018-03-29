package com.cyheng.model.DO;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.util.Date;

/**
 * Created by cyheng on 2018/2/27.
 */

@Data
public class BaseModel {
    //创建时间
    public Date createTime = new Date();
    //更新时间
    @TableField(update = "now()")
    public Date updateTime = new Date();
    private String id;


}
