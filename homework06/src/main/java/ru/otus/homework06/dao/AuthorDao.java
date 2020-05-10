package ru.otus.homework06.dao;

import ru.otus.homework06.entity.Author;

import java.util.Optional;

public interface AuthorDao {

    Author insert(Author author);
    Optional<Author> getById(long id);
    Optional<Author> getByName(String authorName);
    boolean checkByName(String authorName);

}
