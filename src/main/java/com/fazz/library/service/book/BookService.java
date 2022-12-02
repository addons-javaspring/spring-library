package com.fazz.library.service.book;

import com.fazz.library.model.dto.request.BookRequest;
import com.fazz.library.model.dto.response.ResponseData;

public interface BookService {
  ResponseData addBook(BookRequest request);
}
