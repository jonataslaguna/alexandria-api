package com.betrybe.alexandria.service;

import com.betrybe.alexandria.entity.Book;
import com.betrybe.alexandria.entity.BookDetail;
import com.betrybe.alexandria.repository.BookDetailRepository;
import com.betrybe.alexandria.repository.BookRepository;
import com.betrybe.alexandria.service.exception.BookDetailNotFoundException;
import com.betrybe.alexandria.service.exception.BookNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Book service.
 */
@Service
public class BookService {

  private final BookRepository bookRepository;
  private final BookDetailRepository bookDetailRepository;

  /**
   * Instantiates a new Book service.
   *
   * @param bookRepository       the book repository
   * @param bookDetailRepository the book detail repository
   */
  @Autowired
  public BookService(BookRepository bookRepository, BookDetailRepository bookDetailRepository) {
    this.bookRepository = bookRepository;
    this.bookDetailRepository = bookDetailRepository;
  }

  /**
   * Find by id book.
   *
   * @param id the id
   * @return the book
   * @throws BookNotFoundException the book not found exception
   */
  public Book findById(Long id) throws BookNotFoundException {
    return bookRepository.findById(id)
        .orElseThrow(BookNotFoundException::new);
  }

  /**
   * Find all list.
   *
   * @return the list
   */
  public List<Book> findAll() {
    return bookRepository.findAll();
  }

  /**
   * Create book.
   *
   * @param book the book
   * @return the book
   */
  public Book create(Book book) {
    return bookRepository.save(book);
  }

  /**
   * Update book.
   *
   * @param id   the id
   * @param book the book
   * @return the book
   * @throws BookNotFoundException the book not found exception
   */
  public Book update(Long id, Book book) throws BookNotFoundException {
    Book bookFromDb = findById(id);

    bookFromDb.setTitle(book.getTitle());
    bookFromDb.setGenre(book.getGenre());

    return bookRepository.save(bookFromDb);
  }

  /**
   * Delete by id book.
   *
   * @param id the id
   * @return the book
   * @throws BookNotFoundException the book not found exception
   */
  public Book deleteById(Long id) throws BookNotFoundException {
    Book book = findById(id);

    bookRepository.deleteById(id);

    return book;
  }

  /**
   * Create book detail.
   *
   * @param id     the id
   * @param detail the detail
   * @return the book detail
   * @throws BookNotFoundException the book not found exception
   */
  public BookDetail createBookDetail(Long id, BookDetail detail) throws BookNotFoundException {
    Book book = findById(id);

    detail.setBook(book);

    return bookDetailRepository.save(detail);
  }

  /**
   * Gets book detail.
   *
   * @param bookId the book id
   * @return the book detail
   * @throws BookNotFoundException       the book not found exception
   * @throws BookDetailNotFoundException the book detail not found exception
   */
  public BookDetail getBookDetail(Long bookId)
      throws BookNotFoundException, BookDetailNotFoundException {
    Book book = findById(bookId);

    BookDetail bookDetailFromDb = book.getDetail();

    if (bookDetailFromDb == null) {
      throw new BookDetailNotFoundException();
    }

    return bookDetailFromDb;
  }

  /**
   * Update book detail book detail.
   *
   * @param bookId     the book id
   * @param bookDetail the book detail
   * @return the book detail
   * @throws BookNotFoundException       the book not found exception
   * @throws BookDetailNotFoundException the book detail not found exception
   */
  public BookDetail updateBookDetail(Long bookId, BookDetail bookDetail)
      throws BookNotFoundException, BookDetailNotFoundException {
    BookDetail bookDetailFromDb = getBookDetail(bookId);

    bookDetailFromDb.setSummary(bookDetail.getSummary());
    bookDetailFromDb.setPageCount(bookDetail.getPageCount());
    bookDetailFromDb.setYear(bookDetail.getYear());
    bookDetailFromDb.setIsbn(bookDetail.getIsbn());

    return bookDetailRepository.save(bookDetailFromDb);
  }

  /**
   * Remove book detail book detail.
   *
   * @param bookId the book id
   * @return the book detail
   * @throws BookNotFoundException       the book not found exception
   * @throws BookDetailNotFoundException the book detail not found exception
   */
  public BookDetail removeBookDetail(Long bookId)
      throws BookNotFoundException, BookDetailNotFoundException {
    Book book = findById(bookId);
    BookDetail bookDetail = book.getDetail();

    if (bookDetail == null) {
      throw new BookDetailNotFoundException();
    }

    book.setDetail(null);
    bookDetail.setBook(null);

    bookDetailRepository.delete(bookDetail);

    return bookDetail;
  }
}