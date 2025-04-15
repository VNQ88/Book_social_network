package com.vnq.booknetwork.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "VNQ",
                        email = "vnq251103@gmail.com",
                        url = "https://www.facebook.com/vuong.ngo.quoc.2024"
                ),
                description = "OpenApi documentation for Book Network API",
                title = "Open Api specification - Book Network API",
                version = "1.0.0",
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local EVN",
                        url = "http://localhost:8080/api/v1"
                ),
                @Server(
                        description = "Production EVN",
                        url = "https://book-network-production.up.railway.app/api/v1"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                ),
        }
)
@SecurityScheme(
        name = "bearerAuth", //same as @SecurityRequirement name
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
