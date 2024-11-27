package com.henrytran1803.BEBakeManage.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        // Định nghĩa tên của Security Scheme
        String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                .info(new Info()
                        .title("BE Bake Management API")
                        .version("1.0")
                        .description("API documentation for BE Bake Management"))
                // Thêm yêu cầu bảo mật vào tài liệu
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                // Định nghĩa Security Scheme Bearer Token
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT"))); // Định nghĩa là JWT
    }
}
