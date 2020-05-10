package ru.otus.homework06.dao;

import ru.otus.homework06.Exception.EmptyFieldException;
import ru.otus.homework06.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    Book insert(Book book) throws EmptyFieldException;
    Optional<Book> getById(long id);
    List<Book> getAll();
    long getCount();
    void deleteById(long id);

}
