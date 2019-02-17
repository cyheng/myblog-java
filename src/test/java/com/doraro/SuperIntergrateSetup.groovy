package com.doraro

import com.doraro.DoraroBlogApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/***
 *  用于测试mapper
 */
@ContextConfiguration(classes = DoraroBlogApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SuperIntergrateSetup extends Specification {
}
