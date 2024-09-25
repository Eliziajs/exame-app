package com.example.exame_app.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI CustonOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Application for cardiology exam with Java 17 and Spring Boot 4")
                        .version("1.0.0")
                        .description("The application has the function of registering a medical user. " +
                                "The logged-in user can register an already registered patient or register " +
                                "a new patient to perform a new exam. Each patient can have one or more exams. " +
                                "After performing the exam, the user can download the PDF file.")
                        .termsOfService("link")
                        .license(
                                new License()
                                        .name("")
                                        .url("link")));
    }

}
