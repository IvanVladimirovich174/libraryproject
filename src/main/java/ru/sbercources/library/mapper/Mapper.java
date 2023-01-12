package ru.sbercources.library.mapper;

import ru.sbercources.library.dto.GenericDto;
import ru.sbercources.library.model.GenericModel;

public interface Mapper<E extends GenericModel, D extends GenericDto> {

  E toEntity(D dto);

  D toDto(E entity);

}
