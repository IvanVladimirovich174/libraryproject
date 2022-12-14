package ru.sbercources.library.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class GenericModel implements Serializable {

  static final long SerialVersionUID = -4862926644813433707L;

  @Id
  @Column(name="id", nullable = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_generator")
  private Long id;

  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "created_when")
  private LocalDateTime createdWhen;
}
