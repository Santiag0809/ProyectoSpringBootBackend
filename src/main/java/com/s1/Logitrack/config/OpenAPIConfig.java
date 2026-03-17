package com.s1.Logitrack.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("LogiTrack API")
                        .version("1.0")
                        .description("API REST para gestión de bodegas, productos, movimientos y auditorías — LogiTrack 2026."));
    }
}
