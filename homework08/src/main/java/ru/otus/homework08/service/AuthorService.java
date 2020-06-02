package ru.otus.homework08.service;

import ru.otus.homework08.entity.Author;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AuthorService {

    Optional<Author> findByName(String authorName);
    List<Author> findAll();
    Author saveAuthor(String name);
    void deleteById(String id);
    Set<Author> findAuthorsByBookId(String bookId);

}
