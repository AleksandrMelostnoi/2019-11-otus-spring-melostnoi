package ru.otus.homework06.dao;

import ru.otus.homework06.entity.Book;

import java.util.List;

public interface BookDao {

    Book insert(Book book);
    Book getById(long id);
    List<Book> getAll();
    long getCount();
    void deleteById(long id);

}
