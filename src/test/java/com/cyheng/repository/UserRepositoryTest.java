package com.cyheng.repository;

import com.cyheng.model.DO.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void alawaysHaveOneAdminUser() {
        List<User> users = userRepository.selectList(null);
        assertThat(users.size()).isEqualTo(1);
    }
}