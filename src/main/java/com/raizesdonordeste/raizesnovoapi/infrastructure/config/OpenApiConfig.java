package com.raizesdonordeste.raizesnovoapi.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Raízes do Nordeste")
                        .version("1.0")
                        .description("API REST para gerenciamento de pedidos do restaurante Raízes do Nordeste."));
    }
}