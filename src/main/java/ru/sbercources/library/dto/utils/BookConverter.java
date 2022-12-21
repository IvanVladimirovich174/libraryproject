package ru.sbercources.library.dto.utils;

import java.util.function.Function;
import org.springframework.stereotype.Component;
import ru.sbercources.library.dto.BookDto;
import ru.sbercources.library.model.Book;

@Component
public class BookConverter extends Converter<Book, BookDto> {

  public BookConverter() {
    super(BookConverter::convertToEntity, BookConverter::convertToDto);
  }

  private static BookDto convertToDto(Book book) {
    return BookDto
        .builder()
        .id(book.getId())
        .title(book.getTitle())
        .genre(book.getGenre())
        .downloadLink(book.getDownloadLink())
        .storagePlace(book.getStoragePlace())
        .amount(book.getAmount())
        .publishYear(book.getPublishYear())

        .build();
  }

  private static Book convertToEntity(BookDto dto) {
    if (dto.getId() == null) {
      return Book
          .builder()
          .title(dto.getTitle())
          .downloadLink(dto.getDownloadLink())
          .genre(dto.getGenre())
          .storagePlace(dto.getStoragePlace())
          .amount(dto.getAmount())
          .publishYear(dto.getPublishYear())
          .build();
    } else {
      return Book
          .builder()
          .id(dto.getId())
          .title(dto.getTitle())
          .downloadLink(dto.getDownloadLink())
          .genre(dto.getGenre())
          .storagePlace(dto.getStoragePlace())
          .amount(dto.getAmount())
          .publishYear(dto.getPublishYear())
          .build();
    }
  }

}
