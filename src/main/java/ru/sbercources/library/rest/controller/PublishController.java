package ru.sbercources.library.rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.sbercources.library.dto.PublishDto;
import ru.sbercources.library.dto.RentBookDto;
import ru.sbercources.library.mapper.PublishMapper;
import ru.sbercources.library.model.Publish;
import ru.sbercources.library.service.PublishService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rest/publish")
public class PublishController extends GenericController<Publish, PublishDto> {
    private final PublishService service;
    private final PublishMapper mapper;

    protected PublishController(PublishService service, PublishMapper mapper) {
        super(service, mapper);
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("rent-book")
    public PublishDto rentBook(@RequestBody RentBookDto rentBookDto) {
        return mapper.toDto(service.rentBook(rentBookDto));
    }

    @GetMapping("user-publishing/{userId}")
    public List<PublishDto> getUserPublishing(@PathVariable Long userId) {
        return mapper.toDtos(service.getUserPublishing(userId));
    }
}