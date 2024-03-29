package ru.sbercources.library;

import ru.sbercources.library.dto.AuthorDto;

import java.util.List;

public class TestData {

    private static final AuthorDto AUTHOR_DTO = AuthorDto
            .builder()
            .authorFIO("TEST_AUTHOR_FIO")
            .description("TEST_DESC")
            .lifePeriod("TEST_LIFE_PERIOD")
            .build();

    private static final List<AuthorDto> AUTHOR_DTO_LIST = List.of(AUTHOR_DTO, AUTHOR_DTO);
}