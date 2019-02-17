package com.doraro.model.param;

import com.doraro.model.Convert;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;


/**
 * Created by cyheng on 2018/2/25.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ArticleParam extends Convert {
    @NotBlank(message = "不能为空")
    private String title;
    @NotBlank(message = "不能为空")
    private String content;
    @NotBlank(message = "不能为空")
    private String categoryId;
    private String summary;
    @NotNull
    private Boolean published;
    private Boolean showToc;
    private Boolean allowComment;
}
