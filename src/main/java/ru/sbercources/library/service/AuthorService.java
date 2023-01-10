package ru.sbercources.library.service;

import org.springframework.stereotype.Service;
import ru.sbercources.library.model.Author;
import ru.sbercources.library.repository.AuthorRepository;

@Service
public class AuthorService extends GenericService<Author> {

  public AuthorService(AuthorRepository repository) {
    super(repository);
  }

}
