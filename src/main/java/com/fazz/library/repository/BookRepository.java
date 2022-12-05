package com.fazz.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fazz.library.model.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
  // List book by is borrowed
  List<Book> findByIsBorrowed(Boolean isBorrowed);
}
