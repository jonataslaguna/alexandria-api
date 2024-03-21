package com.betrybe.alexandria.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * The type Book.
 */
@Entity
@Table(name = "books")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String genre;
  @OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
  private BookDetail detail;
  @ManyToOne
  @JoinColumn(name = "publisher_id")
  private Publisher publisher;

  /**
   * Instantiates a new Book.
   */
  public Book() {
  }

  /**
   * Instantiates a new Book.
   *
   * @param title the title
   * @param genre the genre
   */
  public Book(String title, String genre) {
    this.title = title;
    this.genre = genre;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets title.
   *
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets title.
   *
   * @param title the title
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Gets genre.
   *
   * @return the genre
   */
  public String getGenre() {
    return genre;
  }

  /**
   * Sets genre.
   *
   * @param genre the genre
   */
  public void setGenre(String genre) {
    this.genre = genre;
  }

  /**
   * Gets detail.
   *
   * @return the detail
   */
  public BookDetail getDetail() {
    return detail;
  }

  /**
   * Sets detail.
   *
   * @param detail the detail
   */
  public void setDetail(BookDetail detail) {
    this.detail = detail;
  }

  /**
   * Gets publisher.
   *
   * @return the publisher
   */
  public Publisher getPublisher() {
    return publisher;
  }

  /**
   * Sets publisher.
   *
   * @param publisher the publisher
   */
  public void setPublisher(Publisher publisher) {
    this.publisher = publisher;
  }
}