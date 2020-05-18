package ru.otus.homework07.service;

import ru.otus.homework07.entity.Genre;

import java.util.List;

public interface GenreService {

    Genre findByName(String genreName);
    List<Genre> findAll();

}
