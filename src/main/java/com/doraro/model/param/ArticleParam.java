package com.doraro.model.param;

import com.doraro.model.Convert;
import com.doraro.model.entity.Article;
import com.doraro.model.entity.BaseModel;
import com.doraro.model.enums.ArticleStatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * Created by cyheng on 2018/2/25.
 */
@Data()
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ArticleParam extends Convert {
    @NotBlank(message = "title不能为空")
    private String title;
    @NotBlank(message = "content不能为空")
    private String content;
    private String summary;
    @NotNull(message = "published不能为空")
    private Boolean published;
    private Boolean showToc;
    private Boolean allowComment;

    @Override
    public <T> T convert(Class<T> clazz) {
        final T convert = super.convert(clazz);
        if (clazz != Article.class) {
            return convert;
        }
        final Article article = (Article) convert;
        if (published) {
            article.setStatus(ArticleStatusEnum.PUBLISH);
        } else {
            article.setStatus(ArticleStatusEnum.DART);
        }
        return (T) article;
    }
}
