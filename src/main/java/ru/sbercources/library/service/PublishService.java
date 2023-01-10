package ru.sbercources.library.service;

import org.springframework.stereotype.Service;
import ru.sbercources.library.model.Publish;
import ru.sbercources.library.repository.GenericRepository;

@Service
public class PublishService extends GenericService<Publish> {

  protected PublishService(GenericRepository<Publish> repository) {
    super(repository);
  }
}
