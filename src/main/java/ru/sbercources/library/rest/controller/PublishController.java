package ru.sbercources.library.rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercources.library.dto.PublishDto;
import ru.sbercources.library.mapper.PublishMapper;
import ru.sbercources.library.model.Publish;
import ru.sbercources.library.service.GenericService;

@Slf4j
@RestController
@RequestMapping("/rest/publish")
public class PublishController extends GenericController<Publish, PublishDto> {

  protected PublishController(GenericService<Publish> service, PublishMapper mapper) {
    super(service, mapper);
  }
}
