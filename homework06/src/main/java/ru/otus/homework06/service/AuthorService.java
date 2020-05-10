package ru.otus.homework06.service;

import ru.otus.homework06.entity.Author;

public interface AuthorService {

    Author getById(long id);
    Author getAuthor(String genreName);

}
