package ru.otus.homework05.dao;

import ru.otus.homework05.model.Author;

import java.util.List;

public interface AuthorRepository {

    List<Author> findAll();
    List<Author> findByName(String fullName);
    Author findById(long id);
    void insert(Author author);
    Author insert(String fullName);
    void update(Author author);
    void deleteById(long id);
    long getNextId();

}
