package ru.sbercources.library.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ru.sbercources.library.model.Book;
import ru.sbercources.library.model.Genre;
import ru.sbercources.library.repository.BookRepository;

@Service
public class BookService extends GenericService<Book> {

  private final BookRepository repository;

  protected BookService(BookRepository repository) {
    super(repository);
    this.repository = repository;
  }

  public List<Book> search(String title, Genre genre) {
    return repository.findAllByGenreOrTitle(
        genre,
        title
    );
  }

}
