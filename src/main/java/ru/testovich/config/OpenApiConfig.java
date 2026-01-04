package ru.testovich.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI getOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Testovich API")
                        .description("API веб-приложения Testovich")
                        .version("1.0.0")
                        .contact(
                            new Contact()
                            .name("DVZH")
                            .email("dvzh07@mail.ru")
                        )
                );
    }
}
