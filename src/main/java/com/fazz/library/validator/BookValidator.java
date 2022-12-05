package com.fazz.library.validator;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fazz.library.exception.custom.NotFoundException;
import com.fazz.library.exception.custom.NotProcessException;
import com.fazz.library.model.entity.Book;

@Service
public class BookValidator {
  public void validateBookNotFound(Optional<Book> bookFind) throws Exception {
    if (bookFind.isEmpty()) {
      // log error book is not found
      throw new NotFoundException("Book is not found!");
    }
  }

  public void validateIsAlreadyDeleted(Book book) throws Exception {
    if (Objects.nonNull(book.getIsDeleted()) && book.getIsDeleted()) {
      throw new NotProcessException("Book is already deleted!");
    }
  }
}
