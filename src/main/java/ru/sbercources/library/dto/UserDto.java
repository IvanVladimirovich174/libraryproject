package ru.sbercources.library.dto;

import lombok.Data;

@Data
public class UserDto {

  private Long id;
  private RoleDto role;
  private String firstName;
  private String lastName;
  private String middleName;
  private String email;
  private String phone;
  private String address;
}
