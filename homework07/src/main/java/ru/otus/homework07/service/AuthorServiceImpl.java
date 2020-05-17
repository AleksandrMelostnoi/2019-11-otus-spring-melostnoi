package ru.otus.homework07.service;

import org.springframework.stereotype.Service;
import ru.otus.homework07.entity.Author;
import ru.otus.homework07.repository.AuthorRepository;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    final private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author findByName(String authorName) {
        if (!authorRepository.findByName(authorName).isPresent()) {
            authorRepository.save(new Author(authorName));
        }
        return authorRepository.findByName(authorName).get();
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}
