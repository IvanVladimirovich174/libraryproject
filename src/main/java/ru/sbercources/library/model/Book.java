package ru.sbercources.library.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_gen", sequenceName = "books_seq", allocationSize = 1)
public class Book extends GenericModel{

  @Column(name = "download_link")
  private String downloadLink;

  @Column(name = "title")
  private String title;

  @Column(name = "genre")
  @Enumerated
  private Genre genre;

  @Column(name = "storage_place")
  private String storagePlace;

  @Column(name = "amount")
  private Integer amount;

  @Column(name = "publish_year")
  private String publishYear;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "books_authors",
      joinColumns = @JoinColumn(name = "book_id"),
      foreignKey = @ForeignKey(name = "FK_BOOKS_AUTHORS"),
      inverseJoinColumns = @JoinColumn(name = "author_id"),
      inverseForeignKey = @ForeignKey(name = "FK_AUTHORS_BOOKS"))
  private Set<Author> authors = new HashSet<>();

}
