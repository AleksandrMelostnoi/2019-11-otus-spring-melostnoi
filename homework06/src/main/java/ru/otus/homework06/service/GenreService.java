package ru.otus.homework06.service;

import ru.otus.homework06.entity.Genre;

public interface GenreService {

    Genre getGenre(String genreName);
    Genre getById(long id);

}
