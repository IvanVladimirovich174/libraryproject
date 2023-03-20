package ru.sbercources.library.dto;

import lombok.*;
import ru.sbercources.library.model.Genre;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto extends GenericDto {
  private String downloadLink;
  private String title;
  private Genre genre;
  private String storagePlace;
  private Integer amount;
  private String publishYear;
  private Set<Long> authorsIds;
}