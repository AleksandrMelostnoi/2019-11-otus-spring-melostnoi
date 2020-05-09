package ru.otus.homework05.service;

import ru.otus.homework05.model.Author;

public interface AuthorService {

    Author getById(long id);
    Author getAuthor(String genreName);

}
