package com.fazz.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fazz.library.model.dto.request.BookRequest;
import com.fazz.library.model.dto.response.ResponseData;
import com.fazz.library.service.book.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {
  @Autowired
  private BookService bookService;

  private ResponseData responseData;

  @PostMapping
  public ResponseEntity<ResponseData> createBook(@RequestBody @Valid BookRequest request) {
    responseData = bookService.addBookService(request);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }

  @GetMapping
  public ResponseEntity<ResponseData> getBooks(@RequestParam(value = "status", defaultValue = "") Boolean status) {
    responseData = bookService.getBooksService(status);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }

  @GetMapping("/{idBook}")
  public ResponseEntity<ResponseData> getBookById(@PathVariable Long idBook) throws Exception {
    responseData = bookService.getBookByIdService(idBook);
    return ResponseEntity.ok().body(responseData);
  }

  @PutMapping("/{idBook}")
  public ResponseEntity<ResponseData> updateBook(@PathVariable Long idBook, @RequestBody @Valid BookRequest request)
      throws Exception {
    responseData = bookService.updateBookService(idBook, request);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }

  @DeleteMapping("/{idBook}")
  public ResponseEntity<ResponseData> deleteBook(@PathVariable Long idBook) throws Exception {
    responseData = bookService.deleteBookService(idBook);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }
}
