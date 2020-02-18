package ru.otus.homework05.dao;

import ru.otus.homework05.model.Author;
import ru.otus.homework05.model.Book;
import ru.otus.homework05.model.Genre;

import java.util.List;

public interface BookRepository {

    List<Book> findAll();
    Book findById(long id);
    List<Book> findByName(String name);
    void insert(Book book);
    Book insert(String name, Author author, Genre genre);
    void update(Book book);
    void deleteById(long id);
    int count();
    List<Book> findByAuthorName(String authorName);
    List<Book> findByGenre(String genreName);
    void insert(long id, String name, String authorName, String genreName);
    long getNextId();

}
