package com.springboot.blog.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springBlogAPI() {
        return new OpenAPI()
                .info(new Info().title("Spring Boot Blog API")
                        .description("Spring Boot Blog REST API sample application")
                        .version("v1.0.0"));
    }

}
