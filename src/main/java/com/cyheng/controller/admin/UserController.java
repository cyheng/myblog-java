package com.cyheng.controller.admin;

import com.cyheng.model.VO.ResultBean;
import com.cyheng.model.VO.UserParam;
import com.cyheng.service.UserService;
import com.cyheng.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cyheng on 2018/2/23.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/api/admin/user/uploadAvatar")
    public ResultBean uploadFile(@RequestParam("file") MultipartFile uploadfile, @RequestHeader(value = "Authorization") String value) throws IOException {
        String token = JwtUtil.extractToken(value);
        return ResultBean.ok(userService.setAvatar(uploadfile, token));
    }
    @GetMapping("/user/info")
    public ResponseEntity getUserInfo(@RequestParam String token) {
        return ResponseEntity.ok(userService.getUserInfo(token));
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody UserParam user) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", userService.getToken(user));
        return ResponseEntity.ok(map);
    }


}
