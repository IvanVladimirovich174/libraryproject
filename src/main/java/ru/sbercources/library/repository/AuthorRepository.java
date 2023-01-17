package ru.sbercources.library.repository;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sbercources.library.model.Author;
import ru.sbercources.library.model.Book;

@Repository
public interface AuthorRepository extends GenericRepository<Author> {

  Set<Author> findAllByIdIn(Set<Long> ids);

  List<Author> findAllByAuthorFIO(String authorFIO);

}
