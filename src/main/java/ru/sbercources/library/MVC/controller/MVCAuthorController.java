package ru.sbercources.library.MVC.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sbercources.library.dto.AddBookDto;
import ru.sbercources.library.dto.AuthorDto;
import ru.sbercources.library.mapper.AuthorMapper;
import ru.sbercources.library.mapper.BookMapper;
import ru.sbercources.library.model.Author;
import ru.sbercources.library.service.AuthorService;
import ru.sbercources.library.service.BookService;

@Controller
@Slf4j
@RequestMapping("/authors")
public class MVCAuthorController {

  private final BookService bookService;
  private final BookMapper bookMapper;
  private final AuthorService service;
  private final AuthorMapper mapper;

  public MVCAuthorController(BookService bookService, BookMapper bookMapper, AuthorService service, AuthorMapper mapper) {
    this.bookService = bookService;
    this.bookMapper = bookMapper;
    this.service = service;
    this.mapper = mapper;
  }

  @GetMapping("")
  public String getAll(
      @RequestParam(value = "page", defaultValue = "1") int page,
      @RequestParam(value = "size", defaultValue = "5") int pageSize,
      Model model
  ) {
    PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Direction.ASC, "authorFIO"));
    Page<Author> authorPage = service.listAllPaginated(pageRequest);
    List<AuthorDto> authorDtos = authorPage
        .stream()
        .map(mapper::toDto)
        .toList();
    model.addAttribute("authors", new PageImpl<>(authorDtos, pageRequest, authorPage.getTotalElements()));
    return "authors/viewAllAuthors";
  }

  @GetMapping("/add")
  public String create(@ModelAttribute("authorForm") AuthorDto authorDTO) {
    return "/authors/addAuthor";
  }

  @PostMapping("/add")
  public String create(
      @ModelAttribute("authorForm") @Valid AuthorDto authorDTO, BindingResult result) {
    if (result.hasErrors()) {
      return "/authors/addAuthor";
    }
    else {
      service.create(mapper.toEntity(authorDTO));
      return "redirect:/authors";
    }

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
  public String searchByAuthorFIO(
      @RequestParam(value = "page", defaultValue = "1") int page,
      @RequestParam(value = "size", defaultValue = "10") int pageSize,
      Model model, @ModelAttribute("searchAuthors") AuthorDto authorDto
  ) {
    if (authorDto.getAuthorFIO().trim().equals("")) {
      model.addAttribute("authors", mapper.toDtos(service.listAll()));
    } else {
      PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Direction.ASC, "authorFIO"));
      Page<Author> authorPage = service.searchByAuthorFIO(pageRequest, authorDto.getAuthorFIO());
      List<AuthorDto> authorDtos = authorPage
          .stream()
          .map(mapper::toDto)
          .toList();
      model.addAttribute("authors", new PageImpl<>(authorDtos, pageRequest, authorPage.getTotalElements()));
    }
    return "authors/viewAllAuthors";
  }


  @GetMapping("/add-book/{authorId}")
  public String addBook(Model model, @PathVariable Long authorId) {
    model.addAttribute("books", bookMapper.toDtos(bookService.listAll()));
    model.addAttribute("authorId", authorId);
    model.addAttribute("author", mapper.toDto(service.getOne(authorId)).getAuthorFIO());
    return "authors/addAuthorBook";
  }

  @PostMapping("/add-book")
  public String addBook(@ModelAttribute("authorBookForm") AddBookDto addBookDto) {
    service.addBook(addBookDto);
    return "redirect:/authors";
  }

}
