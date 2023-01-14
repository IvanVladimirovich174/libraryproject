package ru.sbercources.library.MVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sbercources.library.dto.AuthorDto;
import ru.sbercources.library.mapper.AuthorMapper;
import ru.sbercources.library.service.AuthorService;

@Controller
@RequestMapping("/authors")
public class MVCAuthorController {

  private final AuthorService service;
  private final AuthorMapper mapper;

  public MVCAuthorController(AuthorService service, AuthorMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @GetMapping("")
  public String getAll(Model model) {
    model.addAttribute("authors", service.listAll().stream().map(mapper::toDto).toList());
    return "authors/viewAllAuthors";
  }

  @GetMapping("/add")
  public String create() {
    return "authors/addAuthor"; //путь до html файла
  }

  @PostMapping("/add")
  public String create(@ModelAttribute("authorForm") AuthorDto authorDto) {
    service.create(mapper.toEntity(authorDto));
    return "redirect:/authors";
  }
}
