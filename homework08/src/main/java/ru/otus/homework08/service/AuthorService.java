package ru.otus.homework08.service;

import ru.otus.homework08.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> findByName(String authorName);
    List<Author> findAll();
    void saveAuthor(String name);
    void deleteById(String id);

}
