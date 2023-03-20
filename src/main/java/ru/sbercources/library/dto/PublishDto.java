package ru.sbercources.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublishDto extends GenericDto {
  private LocalDateTime rentDate;
  private LocalDateTime returnDate;
  private boolean returned;
  private Integer rentPeriod;
  private Integer amount;
  private Long bookId;
  private Long userId;
  private BookDto book;
  private UserDto user;
}