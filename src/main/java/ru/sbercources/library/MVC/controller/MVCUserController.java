package ru.sbercources.library.MVC.controller;

import io.swagger.v3.oas.annotations.Hidden;
import java.time.LocalDate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sbercources.library.dto.PublishDto;
import ru.sbercources.library.dto.UserDto;
import ru.sbercources.library.mapper.UserMapper;
import ru.sbercources.library.model.Publish;
import ru.sbercources.library.model.User;
import ru.sbercources.library.service.UserService;
import ru.sbercources.library.service.userDetails.CustomUserDetails;

@Slf4j
@Hidden
@Controller
@RequestMapping("/users")
public class MVCUserController {

  private final UserService service;
  private final UserMapper mapper;

  public MVCUserController(UserService service, UserMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @GetMapping("/registration")
  public String registration() {
    return "registration";
  }

  @PostMapping("/registration")
  public String registration(@ModelAttribute("userForm") UserDto userDto) {
    System.out.println(userDto);
    service.create(mapper.toEntity(userDto));
    return "redirect:login";
  }

  @GetMapping("/profile/{id}")
  public String getProfile(@PathVariable Integer id, Model model) {
    CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if(!id.equals(customUserDetails.getUserId())) {
      return HttpStatus.FORBIDDEN.toString();
    }

    model.addAttribute("user", mapper.toDto(service.getOne(Long.valueOf(id))));
    return "profile/viewProfile";
  }

  @GetMapping("/profile/update/{id}")
  public String update(@PathVariable Integer id, Model model) {
    CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if(!id.equals(customUserDetails.getUserId())) {
      return HttpStatus.FORBIDDEN.toString();
    }
    model.addAttribute("user", mapper.toDto(service.getOne(Long.valueOf(id))));
    return "profile/updateProfile";
  }

  @PostMapping("/profile/update")
  public String update(@ModelAttribute("userForm") UserDto userDto) {
    User foundedUser = service.getOne(userDto.getId());
    foundedUser.setLogin(userDto.getLogin());
    foundedUser.setFirstName(userDto.getFirstName());
    foundedUser.setLastName(userDto.getLastName());
    foundedUser.setMiddleName(userDto.getMiddleName());
    foundedUser.setEmail(userDto.getEmail());
    foundedUser.setBirthDate(userDto.getBirthDate());
    foundedUser.setPhone(userDto.getPhone());
    foundedUser.setAddress(userDto.getAddress());
    userDto = mapper.toDto(foundedUser);
    service.update(mapper.toEntity(userDto));
    return "redirect:/users/profile/" + userDto.getId();
  }

  @GetMapping("/list")
  public String userBooks(
      @RequestParam(value = "page", defaultValue = "1") int page,
      @RequestParam(value = "size", defaultValue = "10") int pageSize,
      Model model
  ) {
    PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
    Page<User> userPage = service.listAllPaginated(pageRequest);
    List<UserDto> userDtos = userPage
        .stream()
        .map(mapper::toDto)
        .toList();
    model.addAttribute("users", new PageImpl<>(userDtos, pageRequest, userPage.getTotalElements()));
    return "users/viewAllUsers";
  }
}
