package com.cyheng;

import com.cyheng.model.DO.SinglePage;
import com.cyheng.model.DO.User;
import com.cyheng.repository.SinglePageRepository;
import com.cyheng.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Slf4j
@Component
public class InitUserDataBase implements CommandLineRunner {
    private UserRepository userRepository;
    private SinglePageRepository singlePageRepository;

    @Value("${admin.username}")
    private String username;

    @Value("${admin.password}")
    private String password;

    @Autowired
    public InitUserDataBase(UserRepository userRepository, SinglePageRepository singlePageRepository) {
        this.userRepository = userRepository;
        this.singlePageRepository = singlePageRepository;
    }

    @Override
    public void run(String... args) {
        initUser();
        initAbout();

    }

    private void initAbout() {
        log.info("init about");
        if (!singlePageRepository.selectList(null).isEmpty()) {
            log.info("about has been init");
            return;
        }
        SinglePage singlePage = new SinglePage("about", "");
        singlePageRepository.insert(singlePage);
    }

    private void initUser() {
        log.info("init database");
        if (!userRepository.selectList(null).isEmpty()) {
            log.info("user has been init");
            return;
        }
        User user = new User(username, password, "ADMIN");
        userRepository.insert(user);
    }
}
