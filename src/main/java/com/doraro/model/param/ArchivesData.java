package com.doraro.model.param;

import com.doraro.model.Convert;
import com.doraro.model.dto.ArchivesArticleView;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

@Data
public class ArchivesData extends Convert {
    @JsonFormat(pattern = "yyyy")
    private String createdYear;
    private List<ArchivesArticleView> articleList;
}
