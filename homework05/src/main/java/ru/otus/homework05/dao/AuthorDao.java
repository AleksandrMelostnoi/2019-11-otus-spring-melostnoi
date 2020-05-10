package ru.otus.homework05.dao;

import ru.otus.homework05.model.Author;

public interface AuthorDao {

    void insert(Author author);
    Author getById(long id);
    Author getByName(String authorName);
    boolean checkByName(String authorName);

}
