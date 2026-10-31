package tn.esprit.autoloc.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI autolocOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Autoloc API")
                .description("API REST de gestion de location automobile — ESPRIT 2026\n\n" +
                    "Cette API expose les opérations CRUD pour la gestion des **Véhicules**, **Clients** et **Agences**.")
                .version("v1.0.0")
                .contact(new Contact()
                    .name("Équipe Autoloc - ESPRIT")
                    .email("autoloc@esprit.tn")
                    .url("https://esprit.tn"))
                .license(new License()
                    .name("MIT License")
                    .url("https://opensource.org/licenses/MIT")))
            .servers(List.of(
                new Server()
                    .url("http://localhost:8089")
                    .description("Serveur de développement local")
            ));
    }
}
