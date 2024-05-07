package com.example.springcoffeeshop.util.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Monkeontheroof",
                        email = "anonymous@example.com",
                        url = "http://localhost:8080/api/swagger-ui/index.html"
                ),
                description = "OpenAPI documentation for Spring Security",
                title = "OpenAPI specification - Spring Coffee Shop",
                version = "1.0",
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080/api",
                        description = "Local ENV"
                )
        }
)
@SecurityScheme(
        name = "bearerToken",
        description = "JWT Authorization & Authentication",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfiguration {
}
