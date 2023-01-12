package ru.sbercources.library.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDto extends GenericDto {

  private String authorFIO;
  private String lifePeriod;
  private String description;
  private Set<Long> booksIds;

}
