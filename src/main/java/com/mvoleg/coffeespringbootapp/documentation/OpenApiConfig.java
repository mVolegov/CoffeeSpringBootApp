package com.mvoleg.coffeespringbootapp.documentation;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Info info = new Info()
                .title("Spring Boot coffee app")
                .description("This is backend part for mobile app");

        return new OpenAPI().info(info);
    }
}
