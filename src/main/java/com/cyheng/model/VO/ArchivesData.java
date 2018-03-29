package com.cyheng.model.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

@Data
public class ArchivesData {
    @JsonFormat(pattern = "yyyy")
    private String createdYear;
    private List<ArchivesArticle> articleList;
}
