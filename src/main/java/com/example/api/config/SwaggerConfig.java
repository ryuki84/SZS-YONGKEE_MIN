package com.example.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger 설정 클래스
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("삼쩜삼 API SZS-민용기")
                        .version("1.0")
                        .description("삼쩜삼 백엔드 엔지니어 채용 과제 API 명세서 By SZS-민용기"));
    }
    
}
