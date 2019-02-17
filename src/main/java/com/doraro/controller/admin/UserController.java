package com.doraro.controller.admin;

import com.doraro.exception.beans.ApiResponses;
import com.doraro.model.param.UserParam;
import com.doraro.service.UserService;
import com.doraro.utils.JwtUtil;
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
    public ApiResponses uploadFile(@RequestParam("file") MultipartFile uploadfile, @RequestHeader(value = "Authorization") String value) throws IOException {
        String token = JwtUtil.extractToken(value);
        return ApiResponses.ok(userService.setAvatar(uploadfile, token));
    }

    @GetMapping("/api/user/info")
    public ResponseEntity getUserInfo(@RequestParam String token) {
        return ResponseEntity.ok(userService.getUserInfo(token));
    }

    @PostMapping("/api/login")
    public ResponseEntity login(@Valid @RequestBody UserParam user) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", userService.getToken(user));
        return ResponseEntity.ok(map);
    }


}
