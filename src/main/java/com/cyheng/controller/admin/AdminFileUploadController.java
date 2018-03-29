package com.cyheng.controller.admin;

import com.cyheng.model.VO.ResultBean;
import com.cyheng.utils.QiNiuUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
public class AdminFileUploadController {


    @Autowired
    private QiNiuUtil qiNiuUtil;

    @PostMapping("/api/admin/uploadFile")
    public ResultBean uploadFile(@RequestParam("file") MultipartFile uploadfile) throws IOException {
        return ResultBean.ok(qiNiuUtil.upLoadfile(uploadfile.getInputStream()));
    }
}
