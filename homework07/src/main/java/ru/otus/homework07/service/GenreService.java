package ru.otus.homework07.service;

import ru.otus.homework07.entity.Genre;

public interface GenreService {

    Genre findByName(String name);
    Genre findById(long id);

}
