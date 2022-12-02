package com.fazz.library.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String author;

  @Column(nullable = false)
  private String publisher;

  @Column(columnDefinition = "LONGTEXT")
  private String synopsis;

  private Boolean isBorrowed = false;

  public Book(String title, String author, String publisher, String synopsis) {
    this.title = title;
    this.author = author;
    this.publisher = publisher;
    this.synopsis = synopsis;
  }
}
