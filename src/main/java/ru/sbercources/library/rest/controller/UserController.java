package ru.sbercources.library.rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercources.library.model.User;
import ru.sbercources.library.repository.GenericRepository;

@Slf4j
@RestController
@RequestMapping("/rest/user")
public class UserController extends GenericController<User> {

  protected UserController(GenericRepository<User> repository) {
    super(repository);
  }
}
