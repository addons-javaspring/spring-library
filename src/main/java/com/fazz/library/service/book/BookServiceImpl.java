package com.fazz.library.service.book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fazz.library.model.dto.request.BookRequest;
import com.fazz.library.model.dto.response.ResponseData;
import com.fazz.library.model.entity.Book;
import com.fazz.library.model.entity.Publisher;
import com.fazz.library.repository.BookRepository;
import com.fazz.library.repository.PublisherRepository;
import com.fazz.library.validator.BookValidator;

@Service
@Transactional
public class BookServiceImpl implements BookService {
  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private PublisherRepository publisherRepository;

  @Autowired
  private BookValidator bookValidator;

  private ResponseData responseData;

  @Value("${fazz.lib.private-key}")
  private String privateKey;

  @Override
  public ResponseData addBookService(BookRequest request) {
    // TODO Auto-generated method stub
    Optional<Publisher> publisherFind = publisherRepository.findById(request.getIdPenerbit());

    // validasi

    // get penerbit
    Publisher publisher = publisherFind.get();

    Book book = new Book(request.getJudul(), request.getPenulis(), request.getSinopsis());
    book.setPublisher(publisher);

    bookRepository.save(book);

    responseData = new ResponseData(201, "success", book);
    return responseData;
  }

  @Override
  public ResponseData getBooksService(Boolean isBorrowed) {
    // TODO Auto-generated method stub
    List<Book> books = new ArrayList<>();
    if (Objects.isNull(isBorrowed)) {
      books = bookRepository.findAll();
    } else {
      books = bookRepository.findByIsBorrowed(isBorrowed);
    }

    // response data
    responseData = new ResponseData(200, "success", books);
    return responseData;
  }

  @Override
  public ResponseData getBookByIdService(Long idBook) throws Exception {
    // TODO Auto-generated method stub
    Optional<Book> bookFind = bookRepository.findById(idBook);

    // validate not found book
    bookValidator.validateBookNotFound(bookFind);

    // get data buku
    Book book = bookFind.get();

    // response data
    responseData = new ResponseData(200, "success", book);
    return responseData;
  }

  @Override
  public ResponseData updateBookService(Long idBook, BookRequest request) throws Exception {
    // TODO Auto-generated method stub
    Optional<Book> bookFind = bookRepository.findById(idBook);
    Optional<Publisher> publisherFind = publisherRepository.findById(request.getIdPenerbit());

    // validate not found book dan penerbit
    bookValidator.validateBookNotFound(bookFind);

    // get book dan penerbit
    Publisher publisher = publisherFind.get();
    Book book = bookFind.get();
    book.setAuthor(request.getPenulis());
    book.setPublisher(publisher);
    book.setSynopsis(request.getSinopsis());
    book.setTitle(request.getJudul());

    // save update
    bookRepository.save(book);

    // response data
    responseData = new ResponseData(200, "success", book);
    return responseData;
  }

  @Override
  public ResponseData deleteBookService(Long idBook) throws Exception {
    // TODO Auto-generated method stub
    Optional<Book> bookFind = bookRepository.findById(idBook);

    // validate not found book
    bookValidator.validateBookNotFound(bookFind);

    // get book
    Book book = bookFind.get();

    // validate already deleted or not
    bookValidator.validateIsAlreadyDeleted(book);

    // update deleted
    book.setIsDeleted(true);

    // save
    bookRepository.save(book);

    // responsedata
    responseData = new ResponseData(200, "success", null);

    return responseData;
  }

}
