package com.fazz.library.service.book;

import com.fazz.library.model.dto.request.BookRequest;
import com.fazz.library.model.dto.response.ResponseData;

public interface BookService {
  ResponseData addBookService(BookRequest request);

  ResponseData getBooksService(Boolean isBorrowed);

  ResponseData getBookByIdService(Long idBook) throws Exception;

  ResponseData updateBookService(Long idBook, BookRequest request) throws Exception;

  ResponseData deleteBookService(Long idBook) throws Exception;
}
