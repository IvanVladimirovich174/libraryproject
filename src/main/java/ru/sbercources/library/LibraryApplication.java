package ru.sbercources.library;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.sbercources.library.dto.RoleDto;
import ru.sbercources.library.dto.UserDto;
import ru.sbercources.library.dto.utils.Converter;
import ru.sbercources.library.model.Role;
import ru.sbercources.library.model.User;
import ru.sbercources.library.repository.UserRepository;

@SpringBootApplication
@Slf4j
public class LibraryApplication {

  public static void main(String[] args) {
    SpringApplication.run(LibraryApplication.class, args);
    log.info("Swagger-ui run on: http://localhost:9090/swagger-ui/index.html");
  }

}
