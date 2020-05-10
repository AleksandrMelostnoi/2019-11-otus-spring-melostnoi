package ru.otus.homework06.dao;

import ru.otus.homework06.entity.Genre;

import java.util.Optional;

public interface GenreDao {

    Genre insert(Genre genre);
    Optional<Genre> getById(long id);
    Optional<Genre> getByName(String genreName);
    boolean checkByName(String genreName);

}
