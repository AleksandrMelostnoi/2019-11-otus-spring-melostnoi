package ru.otus.homework07.service;

import ru.otus.homework07.Exception.EmptyFieldException;
import ru.otus.homework07.entity.Book;

import java.util.List;
import java.util.Set;

public interface BookService {

    void save(Book book);
    Book getById(long id);
    List<Book> findAll();
    long getCount();
    void deleteById(long id);
    Book getNewBook() throws EmptyFieldException;
    Set<Book> findByAuthorName(String name);

}
