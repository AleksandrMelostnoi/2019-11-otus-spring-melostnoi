package ru.otus.homework05.dao;

import ru.otus.homework05.model.Genre;

public interface GenreDao {

    void insert(Genre genre);
    Genre getById(long id);
    Genre getByName(String genreName);
    boolean checkByName(String genreName);

}
