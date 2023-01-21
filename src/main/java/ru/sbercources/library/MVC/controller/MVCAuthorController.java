package ru.sbercources.library.MVC.controller;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sbercources.library.dto.AuthorDto;
import ru.sbercources.library.mapper.AuthorMapper;
import ru.sbercources.library.model.Author;
import ru.sbercources.library.service.AuthorService;

@Controller
@Slf4j
@RequestMapping("/authors")
public class MVCAuthorController {

  private final AuthorService service;
  private final AuthorMapper mapper;

  public MVCAuthorController(AuthorService service, AuthorMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @GetMapping("")
  public String getAll(
      @RequestParam(value = "page", defaultValue = "1") int page,
      @RequestParam(value = "size", defaultValue = "5") int pageSize,
      Model model
  ) {
    PageRequest pageRequest = PageRequest.of(page-1, pageSize, Sort.by(Direction.ASC, "authorFIO"));
    Page<Author> authorPage = service.listAllPaginated(pageRequest);
    List<AuthorDto> authorDtos = authorPage
        .stream()
        .map(mapper::toDto)
        .toList();
    model.addAttribute("authors", new PageImpl<>(authorDtos, pageRequest, authorPage.getTotalElements()));
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

  @GetMapping("/delete/{id}")
  public String delete(@PathVariable Long id) {
    service.delete(id);
    return "redirect:/authors";
  }

  @GetMapping("/update/{id}")
  public String update(@PathVariable Long id, Model model) {
    model.addAttribute("author", mapper.toDto(service.getOne(id)));
    return "authors/updateAuthor"; //путь до html файла
  }

  @PostMapping("/update")
  public String update(@ModelAttribute("authorForm") AuthorDto authorDto) {
    service.update(mapper.toEntity(authorDto));
    return "redirect:/authors";
  }

  @PostMapping("/search")
  public String searchByAuthorFIO(Model model, @ModelAttribute("searchAuthors") AuthorDto authorDto) {
    //TODO рассказать в субботу
    if (authorDto.getAuthorFIO().trim().equals("")) {
      model.addAttribute("authors", mapper.toDtos(service.listAll()));
    } else {
      model.addAttribute("authors", service.searchByAuthorFIO(authorDto.getAuthorFIO()));
    }
    return "authors/viewAllAuthors";
  }

}
