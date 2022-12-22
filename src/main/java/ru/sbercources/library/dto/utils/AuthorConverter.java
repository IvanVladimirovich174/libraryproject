package ru.sbercources.library.dto.utils;

import org.springframework.stereotype.Component;
import ru.sbercources.library.dto.AuthorDto;
import ru.sbercources.library.model.Author;

@Component
public class AuthorConverter extends Converter<Author, AuthorDto> {

  public AuthorConverter() {
    super(AuthorConverter::convertToEntity, AuthorConverter::convertToDto);
  }

  private static AuthorDto convertToDto(Author object) {
    return AuthorDto.builder()
        .id(object.getId())
        .authorFIO(object.getAuthorFIO())
        .description(object.getDescription())
        .lifePeriod(object.getLifePeriod())
        .build();
  }

  private static Author convertToEntity(AuthorDto dto) {
    return Author.builder()
        .id(dto.getId())
        .authorFIO(dto.getAuthorFIO())
        .description(dto.getDescription())
        .lifePeriod(dto.getLifePeriod())
        .build();
  }
}
