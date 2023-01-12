package ru.sbercources.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends GenericDto{

  private Long id;
  private RoleDto role;
  private String firstName;
  private String lastName;
  private String login;
  private String password;
  private String middleName;
  private String email;
  private String phone;
  private String address;
}
