package ru.sbercources.library.rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercources.library.dto.UserDto;
import ru.sbercources.library.mapper.UserMapper;
import ru.sbercources.library.model.User;
import ru.sbercources.library.service.GenericService;

@Slf4j
@RestController
@RequestMapping("/rest/user")
public class UserController extends GenericController<User, UserDto> {

  protected UserController(GenericService<User> service, UserMapper mapper) {
    super(service, mapper);
  }
}
