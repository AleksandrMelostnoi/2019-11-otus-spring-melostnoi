package ru.otus.homework05.service;

import ru.otus.homework05.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getBooks();
    Book getById(long id);
    void insertOrUpdate(Book book);
    List<Book> findBook(String name);
    List<Book> findByAuthorName(String authorName);
    List<Book> findByGenreName(String genreName);
    void insert(String name, String authorName, String genreName);
    void delete(long id);

}
