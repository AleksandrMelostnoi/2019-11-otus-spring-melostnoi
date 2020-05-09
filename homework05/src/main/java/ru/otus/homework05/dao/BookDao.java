package ru.otus.homework05.dao;

import ru.otus.homework05.Exception.BookAlreadyExistsException;
import ru.otus.homework05.Exception.EmptyFieldException;
import ru.otus.homework05.model.Book;

import java.util.List;

public interface BookDao {

    long insert(Book book) throws BookAlreadyExistsException, EmptyFieldException;
    Book getById(long id);
    List<Book> getAll();
    long getCount();
    void deleteById(long id);

}
