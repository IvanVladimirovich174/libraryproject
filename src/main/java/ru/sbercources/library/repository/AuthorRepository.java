package ru.sbercources.library.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.sbercources.library.model.Author;

import java.util.Set;

@Repository
public interface AuthorRepository extends GenericRepository<Author> {
    Set<Author> findAllByIdIn(Set<Long> ids);

    Page<Author> findAllByAuthorFIO(Pageable pageable, String authorFIO);

    Page<Author> findAllByIsDeletedFalse(Pageable pageable);

}
