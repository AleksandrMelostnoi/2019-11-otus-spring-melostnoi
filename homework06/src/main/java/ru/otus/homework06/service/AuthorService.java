package ru.otus.homework06.service;

import ru.otus.homework06.entity.Author;

public interface AuthorService {

    Author getById(Long id);
    Author getAuthor(String authorName);

}
