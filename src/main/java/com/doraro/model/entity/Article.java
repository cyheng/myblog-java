package com.doraro.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.doraro.model.entity.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import com.doraro.model.enums.ArticleStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author doraro
 * @since 2019-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("article")
public class Article extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 文章内容(markdown格式)
     */
    private String content;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 是否允许评论(0-不允许 1-允许)
     */
    private Boolean allowComment;

    /**
     * 是否显示导航(0-不显示 1-显示)
     */
    private Boolean showToc;

    /**
     * 发布状态(0-草稿 1-发布 2-删除中)
     */
    private ArticleStatusEnum status;


    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String CONTENT = "content";

    public static final String TITLE = "title";

    public static final String SUMMARY = "summary";

    public static final String ALLOW_COMMENT = "allow_comment";

    public static final String SHOW_TOC = "show_toc";

    public static final String STATUS = "code";

}
