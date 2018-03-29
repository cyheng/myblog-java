package com.cyheng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

@TestConfiguration
@Import({DataBaseConfig.class, MybatisPlusConfig.class})
@MapperScan(value = "com.cyheng.repository")
public class ServiceConfig {
}
