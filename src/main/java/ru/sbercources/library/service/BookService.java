package ru.sbercources.library.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ru.sbercources.library.model.Book;
import ru.sbercources.library.model.Genre;
import ru.sbercources.library.repository.BookRepository;

@Service
public class BookService extends GenericService<Book> {

//  Инжектим конкретный репозиторий для работы с таблицей books
  private final BookRepository repository;

  protected BookService(BookRepository repository) {
    //Передаем этот репозиторй в абстрактный севрис,
    //чтобы он понимал с какой таблицей будут выполняться CRUD операции
    super(repository);
    this.repository = repository;
  }

  /**
   * Метод для поиска книги
   * Этот метод относится только к книгам, поэтому его реализация пишется только в BookService
   * @param title - Название книги
   * @param genre - Жанр
   * @return - Лист найденных книг
   */
  public List<Book> search(String title, Genre genre) {
    return repository.findAllByGenreOrTitle(
        genre,
        title
    );
  }

}
