package ru.sbercources.library.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercources.library.model.GenericModel;
import ru.sbercources.library.repository.GenericRepository;

@RestController
public abstract class GenericController<T extends GenericModel> {

  private final GenericRepository<T> repository;

  protected GenericController(GenericRepository<T> repository) {
    this.repository = repository;
  }

  @Operation(description = "Получить список всех записей", method = "GetAll")
  @GetMapping("/list")
  ResponseEntity<?> getAll() {
    return ResponseEntity.ok().body(repository.findAll());
  }

  @Operation(description = "Получить запись по id", method = "GetOne")
  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(repository.findById(id).orElseThrow());
  }

  @Operation(description = "Создать запись", method = "Create")
  @PostMapping
  public ResponseEntity<?> create(@RequestBody T object) {
    return ResponseEntity.status(HttpStatus.OK).body(repository.save(object));
  }

  @Operation(description = "Обновить запись", method = "Update")
  @PutMapping("/{id}")
  public ResponseEntity<?> update(@RequestBody T object, @PathVariable Long id) {
    object.setId(id);
    return ResponseEntity.status(HttpStatus.OK).body(repository.save(object));
  }

  @Operation(description = "Удалить запись", method = "Delete")
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    repository.deleteById(id);
  }
}