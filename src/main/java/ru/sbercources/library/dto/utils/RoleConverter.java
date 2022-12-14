package ru.sbercources.library.dto.utils;

import org.springframework.stereotype.Component;
import ru.sbercources.library.dto.RoleDto;
import ru.sbercources.library.model.Role;

@Component
public class RoleConverter extends Converter<Role, RoleDto> {

  public RoleConverter() {
    super(RoleConverter::convertToEntity, RoleConverter::convertToDto);
  }

  private static RoleDto convertToDto(Role role) {
    return new RoleDto(role.getId(), role.getTitle(), role.getDescription());
  }

  private static Role convertToEntity(RoleDto roleDto) {
    return Role.builder()
        .id(roleDto.getId())
        .description(roleDto.getDescription())
        .title(roleDto.getTitle())
        .build();
  }

}
