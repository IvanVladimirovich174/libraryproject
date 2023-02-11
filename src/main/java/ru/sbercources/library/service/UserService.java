package ru.sbercources.library.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sbercources.library.dto.LoginDto;
import ru.sbercources.library.model.Publish;
import ru.sbercources.library.model.User;
import ru.sbercources.library.repository.UserRepository;

@Slf4j
@Service
public class UserService extends GenericService<User> {

  private final UserRepository repository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final RoleService roleService;

  protected UserService(UserRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleService roleService) {
    super(repository);
    this.repository = repository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.roleService = roleService;
  }

  @Override
  public User create(User user) {
    user.setCreatedBy("REGISTRATION");
    user.setRole(roleService.getOne(1L));
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    return repository.save(user);
  }

  public User createLibrarian(User user) {
    user.setCreatedBy("ADMIN");
    user.setRole(roleService.getOne(2L));
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    return repository.save(user);
  }

  public User getByLogin(String login) {
    return repository.findUserByLogin(login);
  }

  public boolean checkPassword(LoginDto loginDto) {
    return bCryptPasswordEncoder.matches(loginDto.getPassword(), getByLogin(loginDto.getLogin()).getPassword());
  }

  public Page<User> listAllPaginated(Pageable pageable) {
    return repository.findAll(pageable);
  }
}
