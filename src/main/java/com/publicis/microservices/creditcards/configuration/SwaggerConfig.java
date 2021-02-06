package com.publicis.microservices.creditcards.configuration;

import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.not;
import static com.google.common.base.Predicates.or;

/**
 * Configuration class for Swagger.
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(applicationName)
                .apiInfo(apiInfo())
                .select()
                .apis(paths())
                .paths(PathSelectors.any())
                .build();
    }

    private Predicate<RequestHandler> paths() {
        return not(hiddenPaths());
    }


    private Predicate<RequestHandler> hiddenPaths() {
        return or(
                RequestHandlerSelectors.basePackage("org.springframework.boot"),
                RequestHandlerSelectors.basePackage("org.springframework.data.rest.webmvc"),
                RequestHandlerSelectors.basePackage("org.springframework.cloud")
        );
    }



    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Credit Card Processing API")
                .description("This page documents the API for Credit Card Processing Api")
                .build();
    }
}
