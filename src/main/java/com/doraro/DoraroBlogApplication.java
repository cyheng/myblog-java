package com.doraro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.doraro.mapper")
public class DoraroBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoraroBlogApplication.class, args);
    }


}