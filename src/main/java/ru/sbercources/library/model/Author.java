package ru.sbercources.library.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_name", sequenceName = "authors_seq", allocationSize = 1)
public class Author extends GenericModel {

  @Column(name = "author_fio")
  private String authorFIO;

  @Column(name = "life_period")
  private String lifePeriod;

  @Column(name = "description")
  private String description;

  @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
  private Set<Book> books = new HashSet<>();

}
