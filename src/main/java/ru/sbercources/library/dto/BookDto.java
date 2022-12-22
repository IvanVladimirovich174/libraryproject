package ru.sbercources.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sbercources.library.model.Genre;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
  private Long id;
  private String downloadLink;
  private String title;
  private Genre genre;
  private String storagePlace;
  private Integer amount;
  private String publishYear;
}
