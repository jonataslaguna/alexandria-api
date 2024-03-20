package com.betrybe.alexandria.service;

import com.betrybe.alexandria.entity.Book;
import com.betrybe.alexandria.repository.BookRepository;
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

  /**
   * Instantiates a new Book service.
   *
   * @param bookRepository the book repository
   */
  @Autowired
  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
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
    // Pegamos a entidade antes de apagar, para poder retorná-la
    Book book = findById(id);

    bookRepository.deleteById(id);

    return book;
  }
}