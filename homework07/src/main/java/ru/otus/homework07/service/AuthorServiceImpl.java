package ru.otus.homework07.service;

import org.springframework.stereotype.Service;
import ru.otus.homework07.repository.AuthorRepository;
import ru.otus.homework07.entity.Author;

@Service
public class AuthorServiceImpl implements AuthorService {
    final private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author findById(long id) {
        return authorRepository.findById(id).get();
    }

    @Override
    public Author findByName(String name) {
        if (!authorRepository.findByName(name).isPresent()) {
            authorRepository.save(new Author(name));
        }
        return authorRepository.findByName(name).get();
    }

}
