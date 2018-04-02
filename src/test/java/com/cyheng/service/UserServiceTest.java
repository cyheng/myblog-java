package com.cyheng.service;

import com.cyheng.model.DO.User;
import com.cyheng.model.VO.UserDetail;
import com.cyheng.model.VO.UserParam;
import com.cyheng.repository.UserRepository;
import com.cyheng.utils.EncryptUtil;
import com.cyheng.utils.JwtUtil;
import com.cyheng.utils.QiNiuUtil;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {


    @InjectMocks
    private UserService userService;


    @Mock
    private JwtUtil jwtService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private QiNiuUtil qiNiuUtil;




    @Test
    public void getToken() {
        UserParam userParam = new UserParam("admin", "admin");
        User user = new User("admin", EncryptUtil.getSha512("admin"), "ADMIN");
        user.setId(anyString());
        when(userRepository.findUserByName(userParam.getUsername())).thenReturn(user);
        when(jwtService.toToken(anyString())).thenReturn("test token");
        assertThat(userService.getToken(userParam)).isEqualTo("test token");
    }

    @Test
    public void getUserInfo() {
        String token = "test token";
        String id = "test id";
        UserDetail detail = new UserDetail("", "ADMIN", "", "");
        Claims claims = mock(Claims.class);
        when(claims.getId()).thenReturn(id);
        when(jwtService.getClaimsFromToken(token)).thenReturn(claims);
        when(userRepository.findUserById(id)).thenReturn(detail);
        assertThat(userService.getUserInfo(token)).hasFieldOrPropertyWithValue("role", "ADMIN");
    }


}