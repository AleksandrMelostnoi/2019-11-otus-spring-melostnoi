package ru.otus.homework08.service;

import ru.otus.homework08.Exception.AuthorNotFoundException;
import ru.otus.homework08.Exception.EmptyFieldException;
import ru.otus.homework08.entity.Book;

import java.util.List;

public interface BookService {

    void save(Book book);
    Book findById(String id);
    List<Book> findAll();
    long getCount();
    void deleteById(String id);
    Book getNewBook() throws EmptyFieldException;
    List<String> getBooksByAuthorName(String authorName) throws AuthorNotFoundException;

}
