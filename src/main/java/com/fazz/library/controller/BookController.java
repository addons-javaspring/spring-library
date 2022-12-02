package com.fazz.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fazz.library.model.dto.request.BookRequest;
import com.fazz.library.model.dto.response.ResponseData;
import com.fazz.library.service.book.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
  @Autowired
  private BookService bookService;

  private ResponseData responseData;

  @PostMapping
  public ResponseEntity<ResponseData> createBook(@RequestBody BookRequest request) {
    responseData = bookService.addBook(request);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }
}
