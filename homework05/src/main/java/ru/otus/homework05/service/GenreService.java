package ru.otus.homework05.service;

import ru.otus.homework05.model.Genre;

public interface GenreService {

    Genre getGenre(String genreName);
    Genre getById(long id);

}
