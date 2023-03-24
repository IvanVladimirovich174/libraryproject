package ru.sbercources.library.service;


import org.springframework.stereotype.Service;
import ru.sbercources.library.model.Role;
import ru.sbercources.library.repository.RoleRepository;

import java.util.List;

/**
 * Пример сервиса который не привязан к абстрактному сервису,
 * в таком случае все CRUD операции реализуются в этом классе
 */
@Service
public class RoleService {
    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public List<Role> getList() {
        return repository.findAll();
    }

    public Role getOne(Long id) {
        return repository.findById(id).orElseThrow();
    }
}