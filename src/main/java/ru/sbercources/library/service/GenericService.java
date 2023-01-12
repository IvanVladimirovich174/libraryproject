package ru.sbercources.library.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ru.sbercources.library.model.GenericModel;
import ru.sbercources.library.repository.GenericRepository;

@Service
public abstract class GenericService<T extends GenericModel> {

  private final GenericRepository<T> repository;
  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  protected GenericService(GenericRepository<T> repository) {
    this.repository = repository;
  }

  public List<T> listAll() {
    return repository.findAll();
  }

  public T getOne(Long id) {
    return repository.findById(id).orElseThrow();
  }

  public T create(T object) {
    return repository.save(object);
  }

  public T update(T object) {
    return repository.save(object);
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }

}
