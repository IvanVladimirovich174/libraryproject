package ru.sbercources.library.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI libraryProject() {
    return new OpenAPI()
        .info(new Info()
            .description("Сайт библиотеки")
            .title("Библиотека")
            .version("v0.1")
            .contact(new Contact().name("Nikita V."))
        );
  }
}
