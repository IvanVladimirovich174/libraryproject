package ru.sbercources.library.repository;

import org.springframework.stereotype.Repository;
import ru.sbercources.library.model.Publish;

import java.util.List;

@Repository
public interface PublishRepository extends GenericRepository<Publish> {
    List<Publish> findPublishByBookId(Long bookId);
}
