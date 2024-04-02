package com.assembleia.votacao.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private final String apiVersion="v1";

    @Bean
    public OpenAPI openApiSpec(){
        return new OpenAPI().info(
                new Info()
                        .title("Assembleia_votação API")
                        .description("API para gerenciar sessões de votações em assembleias")
                        .version(this.apiVersion));
    }
}
