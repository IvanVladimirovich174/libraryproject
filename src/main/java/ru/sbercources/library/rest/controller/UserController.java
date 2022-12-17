package ru.sbercources.library.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercources.library.model.User;
import ru.sbercources.library.repository.UserRepository;

@Slf4j
@RestController
@RequestMapping("/rest/user")
public class UserController {

  //CRUD - create, read, update, delete

  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Operation(description = "Получить список всех пользователей", method = "getOne")
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public ResponseEntity<?> list() {
    log.info("REST");
    Map<String, Object> response = new HashMap<>();
    response.put("permissions", "[]");
    response.put("token", "token");
    return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
  }

  @GetMapping("/{id}")
  public User getById(@PathVariable Long id) {
    return userRepository.findById(id).orElseThrow();
  }

  @PostMapping
  public User create(@RequestBody User user) {
    return userRepository.save(user);
  }

  @PutMapping("/{id}")
  public User update(@RequestBody User user, @PathVariable Long id) {
    user.setId(id);
    return userRepository.save(user);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
//    userRepository.delete(userRepository.findById(id).orElseThrow());
    userRepository.deleteById(id);
  }

}
