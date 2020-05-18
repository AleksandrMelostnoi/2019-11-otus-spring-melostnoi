package ru.otus.homework07.service;

import ru.otus.homework07.entity.Author;

import java.util.List;

public interface AuthorService {

    Author findByName(String authorName);
    List<Author> findAll();

}
