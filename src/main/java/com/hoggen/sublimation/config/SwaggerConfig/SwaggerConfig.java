package com.hoggen.sublimation.config.SwaggerConfig;


import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;
import java.util.ArrayList;

@Configuration
@EnableSwagger2
@Slf4j
public class SwaggerConfig {
    /*
     * Springfox configuration for the API Swagger docs.
     *
     * @return the Swagger Springfox configuration
     */
    @Bean
    public Docket swaggerSpringfoxApiDocket(ServletContext servletContext) {
        log.info("Starting Swagger");
        StopWatch watch = new StopWatch();
        watch.start();
        Contact contact = new Contact("7聊", "http://localhost:8099", "hoggenwang@qq.com");

        ApiInfo apiInfo = new ApiInfo(
                "7聊api",
                "7聊api",
                "v1.0.0",
                "http://localhost:8099",
                contact,
                "",
                "",
                new ArrayList<>());

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))  //添加ApiOperiation注解的被扫描
                .paths(PathSelectors.any())
                .build();
        watch.stop();
        log.info("Started Swagger in {} ms", watch.getTotalTimeMillis());
        return docket;
    }

}
