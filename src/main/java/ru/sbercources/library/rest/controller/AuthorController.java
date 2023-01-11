package ru.sbercources.library.rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercources.library.model.Author;
import ru.sbercources.library.service.GenericService;


/**
 * Реазация контроллера для сущности Author
 * При наследовании этого класса от GenericController<T>,
 * автоматически реализуются основные CRUD операции
 */
@Slf4j
@RestController
@RequestMapping("/rest/author")
public class AuthorController extends GenericController<Author> {

  public AuthorController(GenericService<Author> service) {
    super(service);
  }
}
