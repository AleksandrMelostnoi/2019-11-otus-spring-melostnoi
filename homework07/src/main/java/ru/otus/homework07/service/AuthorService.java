package ru.otus.homework07.service;

import ru.otus.homework07.entity.Author;

public interface AuthorService {

    Author findById(long id);
    Author findByName(String name);

}
