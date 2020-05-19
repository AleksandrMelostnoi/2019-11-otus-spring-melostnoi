package ru.otus.homework08.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.otus.homework08.entity.Author;
import ru.otus.homework08.entity.Book;
import ru.otus.homework08.repository.AuthorRepository;
import ru.otus.homework08.repository.BookRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Override
    public Optional<Author> findByName(String authorName) {
        return authorRepository.findByName(authorName);
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional
    public Author saveAuthor(String authorName) {
        return authorRepository.save(new Author(authorName));
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        authorRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Author> findAuthorsByBookId(String bookId) {
        Assert.notNull(bookId, "Book id is null !");
        Book book = bookRepository.findById(bookId).orElse(null);
        if (nonNull(book) && isNotEmpty(book.getAuthors())) {
            return book.getAuthors();
        } else {
            return Collections.emptySet();
        }
    }

}
