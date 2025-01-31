package com.anderson.forohub.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("Foro Hub API")
                        .description("API Rest de la aplicación Foro Hub. Contiene las funcionalidades para Listar, Crear, Modificar y Eliminar Tópicos y Cursos."+
                                "Implementa como método de Autenticación y Autorización los JWT Tokens")
                        .contact(new Contact()
                                .name("Anderson Camilo Rodríguez Salvador")
                                .email("andersoncamilo.rodriguez.s@gmail.com"))
                        .version("1.0.0")
                        .license(new License()
                                .name("MIT License")
                                .url("https://github.com/Andersoncrs/Challenge-Foro-Hub-Alura/blob/main/LICENSE")))
                .servers(List.of(new Server().url("http://localhost:8080").description("Servidor Local")))
                .externalDocs(new ExternalDocumentation()
                        .description("Enlace de GitHub del Proyecto")
                        .url("https://github.com/Andersoncrs/Challenge-Foro-Hub-Alura"));
    }
}
