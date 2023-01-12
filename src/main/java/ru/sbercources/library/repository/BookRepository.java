package ru.sbercources.library.repository;

import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Repository;
import ru.sbercources.library.model.Book;
import ru.sbercources.library.model.Genre;

@Repository
public interface BookRepository extends GenericRepository<Book> {

  List<Book> findAllByGenreOrTitle(Genre genre, String title);
  Set<Book> findAllByIdIn(Set<Long> ids);
}
