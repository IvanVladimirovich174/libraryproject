package ru.sbercources.library.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends GenericDto{

  private RoleDto role;
  private String firstName;
  private String lastName;
  private String login;
  private String password;
  private String middleName;
  private String email;
  private String phone;
  private String address;
  //TODO разобраться почему нельзя поставить LocalDate
//  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'")
  private Date birthDate;

}
