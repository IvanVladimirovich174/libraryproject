package ru.sbercources.library.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.sbercources.library.dto.AuthorDto;
import ru.sbercources.library.model.Author;
import ru.sbercources.library.repository.AuthorRepository;

@Service
public class AuthorService extends GenericService<Author> {

  private final AuthorRepository authorRepository;

  public AuthorService(AuthorRepository repository,
      AuthorRepository authorRepository) {
    super(repository);
    this.authorRepository = authorRepository;
  }

  public List<Author> searchByAuthorFIO(String fio) {
    return authorRepository.findAllByAuthorFIO(fio);
  }

  public Page<Author> listAllPaginated(Pageable pageable) {
    return authorRepository.findAll(pageable);
  }

}
