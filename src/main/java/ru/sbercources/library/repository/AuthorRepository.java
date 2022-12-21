package ru.sbercources.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sbercources.library.model.Author;

@Repository
public interface AuthorRepository extends GenericRepository<Author> {

}
