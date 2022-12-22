package ru.sbercources.library.dto.utils;

import java.util.function.Function;
import org.springframework.stereotype.Component;
import ru.sbercources.library.dto.RoleDto;
import ru.sbercources.library.dto.UserDto;
import ru.sbercources.library.model.Role;
import ru.sbercources.library.model.User;

@Component
public class UserConverter extends Converter<User, UserDto> {

  public UserConverter() {
    super(UserConverter::convertToEntity, UserConverter::convertToDto);
  }

  private static UserDto convertToDto(User user) {
    return UserDto.builder()
        .id(user.getId())
        .role(new RoleDto(user.getRole()))
        .login(user.getLogin())
        .password(user.getPassword())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .middleName(user.getMiddleName())
        .email(user.getEmail())
        .address(user.getAddress())
        .phone(user.getPhone())
        .build();
  }

  private static User convertToEntity(UserDto userDto) {
    return User.builder()
        .id(userDto.getId())
        .role(new Role(userDto.getRole()))
        .login(userDto.getLogin())
        .password(userDto.getPassword())
        .firstName(userDto.getLastName())
        .middleName(userDto.getMiddleName())
        .email(userDto.getEmail())
        .phone(userDto.getPhone())
        .address(userDto.getAddress())
        .build();
  }
}
