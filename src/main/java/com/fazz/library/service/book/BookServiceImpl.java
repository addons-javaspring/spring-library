package com.fazz.library.service.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fazz.library.model.dto.request.BookRequest;
import com.fazz.library.model.dto.response.ResponseData;
import com.fazz.library.model.entity.Book;
import com.fazz.library.repository.BookRepository;

@Service
@Transactional
public class BookServiceImpl implements BookService {
  @Autowired
  private BookRepository bookRepository;

  private ResponseData responseData;

  @Override
  public ResponseData addBook(BookRequest request) {
    // TODO Auto-generated method stub
    Book book = new Book(request.getJudul(), request.getPenulis(), request.getPenerbit(), request.getSinopsis());

    bookRepository.save(book);

    responseData = new ResponseData(201, "success", book);
    return responseData;
  }

}
