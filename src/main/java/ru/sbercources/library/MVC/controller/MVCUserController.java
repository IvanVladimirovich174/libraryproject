package ru.sbercources.library.MVC.controller;

import io.swagger.v3.oas.annotations.Hidden;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.sbercources.library.model.User;
import ru.sbercources.library.repository.UserRepository;

@Slf4j
@Hidden
@Controller
@RequestMapping("/user")
public class MVCUserController {

  private final UserRepository userRepository;

  public MVCUserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @ResponseBody
  @GetMapping("/list") //localhoht:9090/user/list
  public List<User> list() {
    return userRepository.findAll();
  }

  @ResponseBody
  @GetMapping("/{id}") //localhoht:9090/user/1
  public User getById(@PathVariable Long id) {
    return userRepository.findById(id).orElseThrow();
  }
}
