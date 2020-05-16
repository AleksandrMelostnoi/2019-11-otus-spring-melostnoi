package ru.otus.homework06.service;

import ru.otus.homework06.Exception.EmptyFieldException;
import ru.otus.homework06.entity.Book;

import java.util.List;
import java.util.Set;

public interface BookService {

    void insert(Book book);
    Book getById(long id);
    List<Book> getAll();
    long getCount();
    void deleteById(long id);
    Book getNewBook() throws EmptyFieldException;
    Set<Book> findByAuthorName(String name);

}
