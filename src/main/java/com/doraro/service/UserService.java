package com.doraro.service;


import com.doraro.exception.ResourcesNotFoundException;
import com.doraro.model.entity.User;
import com.doraro.model.param.UserDetail;
import com.doraro.model.param.UserParam;
import com.doraro.mapper.UserRepository;
import com.doraro.utils.EncryptUtil;
import com.doraro.utils.JwtUtil;
import com.doraro.utils.QiNiuUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by cyheng on 2018/2/24.
 */
@Service
@Slf4j
public class UserService {
    private JwtUtil jwtService;
    private UserRepository userRepository;
    private QiNiuUtil qiNiuUtil;
    @Autowired
    public UserService(
            JwtUtil jwtService, UserRepository userRepository, QiNiuUtil qiNiuUtil) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.qiNiuUtil = qiNiuUtil;
    }


    public String getToken(UserParam user) {
        User userByName = userRepository.findUserByName(user.getUsername());
        if (userByName == null) {
            throw new ResourcesNotFoundException("账号或者密码错误!");
        }
        String md5 = EncryptUtil.getSha512(user.getPassword());
        if (!(userByName.getUsername().equals(user.getUsername()) && userByName.getPassword().equals(md5))) {
            throw new ResourcesNotFoundException("账号或者密码错误！");
        }

//        return jwtService.toToken(userByName.getId());
        return "";
    }

    public UserDetail getUserInfo(String token) {
        Optional<Claims> claimsFromToken = Optional.ofNullable(jwtService.getClaimsFromToken(token));
        return claimsFromToken.map((claims) -> {
            String id = claims.getId();
            return userRepository.findUserById(id);
        }).orElseThrow(ResourcesNotFoundException::new);
    }

    @Transactional
    public String setAvatar(MultipartFile uploadfile, String token) throws IOException {
        Claims claims = jwtService.getClaimsFromToken(token);
        if (claims == null) {
            throw new ResourcesNotFoundException("user not found!");
        }
        String id = claims.getId();
        User user = userRepository.selectById(id);
        String avatarURL = qiNiuUtil.upLoadfile(uploadfile.getInputStream());
        user.setAvatar(avatarURL);
        userRepository.updateById(user);
        return avatarURL;
    }
}