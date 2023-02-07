package ru.sbercources.library.service;

import java.util.List;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.sbercources.library.dto.AddBookDto;
import ru.sbercources.library.model.Author;
import ru.sbercources.library.model.Book;
import ru.sbercources.library.repository.AuthorRepository;
import ru.sbercources.library.repository.BookRepository;
import ru.sbercources.library.repository.PublishRepository;

@Service
public class AuthorService extends GenericService<Author> {

  private final BookRepository bookRepository;
  private final BookService bookService;
  private final AuthorRepository authorRepository;
  private final PublishRepository publishRepository;

  public AuthorService(AuthorRepository repository,
      BookRepository bookRepository, BookService bookService, AuthorRepository authorRepository,
      PublishRepository publishRepository) {
    super(repository);
    this.bookRepository = bookRepository;
    this.bookService = bookService;
    this.authorRepository = authorRepository;
    this.publishRepository = publishRepository;
  }

  public Page<Author> searchByAuthorFIO(Pageable pageable, String fio) {
    return authorRepository.findAllByAuthorFIO(pageable, fio);
  }
  public Page<Author> listAllPaginated(Pageable pageable) {
    return authorRepository.findAll(pageable);
  }

  public Author addBook(AddBookDto addBookDto) {
    Author author = getOne(addBookDto.getAuthorId());
    Book book = bookService.getOne(addBookDto.getBookId());
    author.getBooks().add(book);
    return update(author);
  }

  //TODO посмотреть как можно сократить
  @Override
  public void delete(Long id) {
    List<Book> books = bookRepository.findBooksByAuthorsId(id);
    if(books.isEmpty()) {
      authorRepository.deleteById(id);
      return;
    }
    List<Long> bookIds = books.stream()
        .filter(i -> i.getAuthors().size() == 1)
        .map(Book::getId)
        .toList();

    if (bookIds.isEmpty()) {
      throw new OpenApiResourceNotFoundException("У данного актера нету фильма где он снимался один.");
    }
    bookIds.stream()
        .filter(i -> {
          if (publishRepository.findPublishByBookId(i).isEmpty()) {
            return true;
          } else {
            throw new OpenApiResourceNotFoundException("Фильм с данным актерем в данный момент арендован");
          }
        })
        .forEach(i -> {
          bookRepository.deleteById(i);
          authorRepository.deleteById(id);
        });
  }
}
