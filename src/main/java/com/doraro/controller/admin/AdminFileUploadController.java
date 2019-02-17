package com.doraro.controller.admin;

import com.doraro.exception.beans.ApiResponses;
import com.doraro.utils.QiNiuUtil;
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
    public ApiResponses uploadFile(@RequestParam("file") MultipartFile uploadfile) throws IOException {
        return ApiResponses.ok(qiNiuUtil.upLoadfile(uploadfile.getInputStream()));
    }
}
