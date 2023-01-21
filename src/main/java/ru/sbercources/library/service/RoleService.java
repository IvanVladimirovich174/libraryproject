package ru.sbercources.library.service;


import java.util.List;
import org.springframework.stereotype.Service;
import ru.sbercources.library.model.Role;
import ru.sbercources.library.repository.RoleRepository;

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
