package ru.otus.homework08.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework08.Exception.AuthorNotFoundException;
import ru.otus.homework08.Exception.EmptyFieldException;
import ru.otus.homework08.entity.Author;
import ru.otus.homework08.entity.Book;
import ru.otus.homework08.entity.Genre;
import ru.otus.homework08.repository.BookRepository;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    final private IOService ioService;
    final private BookRepository bookRepository;
    final private GenreService genreService;
    final private AuthorService authorService;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public long getCount() {
        return bookRepository.count();
    }

    @Override
    @Transactional
    public void save(Book book)  {
        bookRepository.save(book);
    }

    @Override
    public Book findById(String id) {
        return bookRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book getNewBook() throws EmptyFieldException {
        ioService.write("Enter title of book");
        String title = ioService.read();

        ioService.write("Enter the author (or authors - separated by commas)");
        String[] authorNames = ioService.read().split(",");
        HashSet<Author> authors = new HashSet<>();
        for (String authorName : authorNames) {
            if (authorService.findByName(authorName.trim()).isEmpty()) {
                authorService.saveAuthor(authorName);
            }
            authors.add(authorService.findByName(authorName).get());
        }

        ioService.write("Enter a genre (or genres - separated by commas)");
        String[] genreNames = ioService.read().split(",");
        HashSet<Genre> genres = new HashSet<>();
        for (String genreName : genreNames) {
            if (genreService.findByName(genreName.trim()).isEmpty()) {
                genreService.saveGenre(genreName);
            }
            genres.add(genreService.findByName(genreName).get());
        }

        return Book.builder()
                .title(title)
                .authors(authors)
                .genres(genres)
                .build();
    }

    @Override
    public List<String> getBooksByAuthorName(String authorName) throws AuthorNotFoundException {
        Author author = authorService.findByName(authorName)
                .orElseThrow(() -> new AuthorNotFoundException(String.format("Author '%s' not found!", authorName)));
        return bookRepository.findAllByAuthorsContaining(author).stream().map(Book::toString).collect(Collectors.toList());
    }

}