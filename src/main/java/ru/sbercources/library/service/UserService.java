package ru.sbercources.library.service;

import org.springframework.stereotype.Service;
import ru.sbercources.library.model.User;
import ru.sbercources.library.repository.GenericRepository;

@Service
public class UserService extends GenericService<User> {

  protected UserService(GenericRepository<User> repository) {
    super(repository);
  }
}
