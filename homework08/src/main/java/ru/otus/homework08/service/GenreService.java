package ru.otus.homework08.service;

import ru.otus.homework08.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    Optional<Genre> findByName(String genreName);
    List<Genre> findAll();
    void saveGenre(String genreName);
    void deleteById(String id);

}
