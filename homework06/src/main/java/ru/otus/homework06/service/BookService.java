package ru.otus.homework06.service;

import ru.otus.homework06.Exception.EmptyFieldException;
import ru.otus.homework06.entity.Book;

import java.util.List;

public interface BookService {

    void insert(Book book) throws EmptyFieldException;
    Book getById(long id);
    List<Book> getAll();
    long getCount();
    void deleteById(long id);
    Book getNewBook();

}
