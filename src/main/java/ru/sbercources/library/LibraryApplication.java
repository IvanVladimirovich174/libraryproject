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
public class LibraryApplication implements CommandLineRunner {

  private final UserRepository userRepository;
  private final Converter<Role, RoleDto> converter;

  public LibraryApplication(UserRepository userRepository,
      Converter<Role, RoleDto> converter) {
    this.userRepository = userRepository;
    this.converter = converter;
  }

  public static void main(String[] args) {
    SpringApplication.run(LibraryApplication.class, args);
  }


  @Override
  public void run(String... args) throws Exception {
    System.out.println(userRepository.findAll());
//    System.out.println(userRepository.findById(1L).orElseThrow());

    User user = new User();
    user.setAddress("test");
    user.setEmail("test");
    user.setFirstName("test");
    user.setLastName("test");
    user.setPhone("test");
    user.setMiddleName("test");
    user.setRole(new Role(1L, "user", "user"));
    user.setCreatedBy("test");
    user.setCreatedWhen(LocalDateTime.now());

//    User createdUser = userRepository.save(user);
//    System.out.println(createdUser);
//    userRepository.delete(createdUser);
//
//    createdUser.setLastName("UPDATED");
//    userRepository.save(createdUser);
//
//    System.out.println(userRepository.findAllByFirstName("test"));
//    System.out.println(userRepository.findAllByFirstNameAndMiddleName("test", "test"));
//    log.error(String.valueOf(userRepository.findAllByCreatedBy("test")));

    User foundedUser = userRepository.findById(2L).orElseThrow();
    RoleDto roleDto = converter.convertFromEntity(foundedUser.getRole());
    Role role = converter.convertFromDto(roleDto);

    System.out.println(roleDto);
    System.out.println(role);

//    log.info(foundedUser.toString());
//    UserDto userDto = new UserDto();
//    userDto.setId(foundedUser.getId());
//    userDto.setRole(new RoleDto(foundedUser.getRole()));
//    userDto.setFirstName(foundedUser.getFirstName());
//    userDto.setLastName(foundedUser.getLastName());
//    userDto.setMiddleName(foundedUser.getMiddleName());
//    userDto.setEmail(foundedUser.getEmail());
//    userDto.setPhone(foundedUser.getPhone());
//    userDto.setAddress(foundedUser.getAddress());

//    log.info(userDto.toString());

  }
}
