package ru.sbercources.library.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.sbercources.library.model.Book;
import ru.sbercources.library.model.Genre;

import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends GenericRepository<Book> {
    List<Book> findAllByGenreOrTitle(Genre genre, String title);

    Set<Book> findAllByIdIn(Set<Long> ids);

    List<Book> findAllByTitle(String title);

    List<Book> findBooksByAuthorsId(Long id);

    Page<Book> findAllByIsDeletedFalse(Pageable pageable);

}
