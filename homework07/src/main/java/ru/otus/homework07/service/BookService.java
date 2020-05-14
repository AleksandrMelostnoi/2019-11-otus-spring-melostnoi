package ru.otus.homework07.service;

import ru.otus.homework07.Exception.EmptyFieldException;
import ru.otus.homework07.entity.Book;

import java.util.List;

public interface BookService {

    Book save(Book book) throws EmptyFieldException;
    Book findById(long id);
    List<Book> findAll();
    long getCount();
    void deleteById(long id);
    Book getNewBook();
    List<Book> findByAuthorName(String name);

}
