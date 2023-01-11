package ru.sbercources.library.rest.controller;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercources.library.model.Book;
import ru.sbercources.library.model.Genre;
import ru.sbercources.library.service.BookService;

@Slf4j
@RestController
@RequestMapping("/rest/book")
public class BookController extends GenericController<Book> {

  private final BookService service;

  public BookController(BookService service) {
    super(service);
    this.service = service;
  }

  @GetMapping("/search")
  public List<Book> search(
      @RequestParam(value = "title", required = false) String title,
      @RequestParam(value = "genre", required = false) Genre genre
  ) {
    return service.search(title, genre);
  }

}
