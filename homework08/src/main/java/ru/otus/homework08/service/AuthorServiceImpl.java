package ru.otus.homework08.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework08.entity.Author;
import ru.otus.homework08.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    final private AuthorRepository authorRepository;

    @Override
    public Optional<Author> findByName(String authorName) {
        return authorRepository.findByName(authorName);
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional
    public void saveAuthor(String authorName) {
        authorRepository.save(new Author(authorName));
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        authorRepository.deleteById(id);
    }

}
