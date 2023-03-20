package ru.sbercources.library.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDto extends GenericDto {
  @NotBlank(message = "Поле не должно быть пустым")
  private String authorFIO;
  @NotBlank(message = "Поле не должно быть пустым")
  private String lifePeriod;
  @NotBlank(message = "Поле не должно быть пустым")
  private String description;
  private Set<Long> booksIds;

}