package com.doraro.config;

import com.doraro.utils.Constant;
import com.doraro.utils.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.json.Json;
import springfox.documentation.spring.web.json.JsonSerializer;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * Created by cyheng on 2017/11/1.
 */

@Configuration
@EnableSwagger2
public class swaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(getParameters())
                .apiInfo(apiInfo())
                .directModelSubstitute(LocalDate.class, Date.class)
                .directModelSubstitute(LocalTime.class, Date.class)
                .directModelSubstitute(Date.class, Long.class)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private List<Parameter> getParameters() {
        return Collections.singletonList(new ParameterBuilder()
                .name(Constant.TOKEN_KEY)
                .defaultValue(Constant.TOKEN_PREFIX)
                .description(Constant.TOKEN_KEY)
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("DORARO_BLOG api")
                .description("DORARO BLOG api 接口")
                .version("1.0")
                .build();
    }


}
