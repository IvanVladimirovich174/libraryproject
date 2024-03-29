package ru.sbercources.library.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends GenericDto{
  private RoleDto role;
  @NotBlank(message = "Поле не должно быть пустым")
  private String firstName;
  @NotBlank(message = "Поле не должно быть пустым")
  private String lastName;
  @NotBlank(message = "Поле не должно быть пустым")
  private String login;
  @NotBlank(message = "Поле не должно быть пустым")
  private String password;
  @NotBlank(message = "Поле не должно быть пустым")
  private String middleName;
  @NotBlank(message = "Поле не должно быть пустым")
  private String email;
  @NotBlank(message = "Поле не должно быть пустым")
  private String phone;
  @NotBlank(message = "Поле не должно быть пустым")
  private String address;
  //TODO разобраться почему нельзя поставить LocalDate
//  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'")
  private Date birthDate;
}