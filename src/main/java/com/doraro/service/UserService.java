package com.doraro.service;


import com.doraro.model.param.UserDetail;
import com.doraro.model.param.SysUserParam;
import com.doraro.mapper.UserMapper;
import com.doraro.utils.QiNiuUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by cyheng on 2018/2/24.
 */
@Service
@Slf4j
public class UserService {
    private UserMapper userMapper;
    private QiNiuUtil qiNiuUtil;
    @Autowired
    public UserService(
            UserMapper userMapper, QiNiuUtil qiNiuUtil) {

        this.qiNiuUtil = qiNiuUtil;
    }


    public String getToken(SysUserParam user) {


//        return jwtService.toToken(userByName.getId());
        return "";
    }

    public UserDetail getUserInfo(String token) {
//        Optional<Claims> claimsFromToken = Optional.ofNullable(jwtService.getClaimsFromToken(token));
//        return claimsFromToken.map((claims) -> {
//            String id = claims.getId();
//            return userMapper.findUserById(id);
//        }).orElseThrow(ResourcesNotFoundException::new);
        return null;
    }

    @Transactional
    public String setAvatar(MultipartFile uploadfile) throws IOException {
//        Claims claims = jwtService.getClaimsFromToken(token);
//        if (claims == null) {
//            throw new ResourcesNotFoundException("user not found!");
//        }
//        String id = claims.getId();
//        User user = userMapper.selectById(id);
//        String avatarURL = qiNiuUtil.upLoadfile(uploadfile.getInputStream());
////        user.setAvatar(avatarURL);
//        userMapper.updateById(user);
//        return avatarURL;
        return null;
    }
}
