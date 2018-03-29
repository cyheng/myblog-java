package com.cyheng.controller;

import com.cyheng.model.VO.ArchivesArticle;
import com.cyheng.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ArchivesController {

    @Autowired
    private ArticleService articleService;

    @GetMapping(value = "/api/archives")
    public Map<Integer, List<ArchivesArticle>> getArchives() {
        return articleService.getArchives();
    }
}
