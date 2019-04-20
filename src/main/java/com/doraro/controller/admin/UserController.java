package com.doraro.controller.admin;

import com.doraro.exception.beans.ApiResponses;
import com.doraro.exception.beans.ErrorCodeEnum;
import com.doraro.model.entity.SysUser;
import com.doraro.model.param.SysUserParam;
import com.doraro.service.ISysUserService;
import com.doraro.service.impl.CatchaService;
import com.doraro.utils.JwtUtil;
import com.wf.captcha.Captcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * Created by cyheng on 2018/2/23.
 */
@RestController
@Api(description = "用户相关接口", tags = "user")
public class UserController {
    private final ISysUserService userService;
    private CatchaService captchaService;
    @Autowired
    public UserController(ISysUserService userService, CatchaService captchaService) {
        this.userService = userService;
        this.captchaService = captchaService;
    }


    @PostMapping("/api/admin/user/uploadAvatar")
    public ApiResponses uploadFile(@RequestParam("file") MultipartFile uploadfile) throws IOException {
        return ApiResponses.ok(userService.setAvatar(uploadfile));
    }

    @GetMapping("/api/user/info")
    @ApiOperation("通过token获得用户信息")
    public ApiResponses getUserInfo(@RequestParam String token) {
        return ApiResponses.ok(userService.getUserInfo(token));
    }

    @ApiOperation("登录并获取jwt token")
    @PostMapping("/api/login")
    public ApiResponses login(@Valid @RequestBody SysUserParam user, HttpServletRequest request) {
        boolean capCheck = captchaService.checkCaptcha(user.getUuid(), user.getCaptcha());
        if (!capCheck) {
            return ApiResponses.failure(ErrorCodeEnum.CAPTCHA_IS_WRONG);
        }
        final SysUser sysUser = userService.login(user, request);
        return ApiResponses.ok(JwtUtil.toToken(sysUser.getId()));
    }

    @GetMapping(value = "captcha.jpg", produces = MediaType.IMAGE_JPEG_VALUE)
    @ApiOperation("获得验证码")
    public void captcha(HttpServletResponse response, @RequestParam String uuid) throws IOException {
        CaptchaUtil.setHeader(response);
        GifCaptcha gifCaptcha = new GifCaptcha(130, 48, 5);
        gifCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
        captchaService.saveCaptcha(uuid, gifCaptcha.text().toLowerCase());
        gifCaptcha.out(response.getOutputStream());
    }

}
