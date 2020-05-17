package ru.otus.homework07.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework07.Exception.EmptyFieldException;
import ru.otus.homework07.entity.Author;
import ru.otus.homework07.entity.Book;
import ru.otus.homework07.entity.Genre;
import ru.otus.homework07.repository.BookRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {
    final private IOService ioService;
    final private BookRepository bookRepository;
    final private GenreService genreService;
    final private AuthorService authorService;

    @Autowired
    public BookServiceImpl(IOService ioService, BookRepository bookRepository, GenreService genreService, AuthorService authorService) {
        this.ioService = ioService;
        this.bookRepository = bookRepository;
        this.genreService = genreService;
        this.authorService = authorService;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public long getCount() {
        return bookRepository.count();
    }

    @Override
    public void save(Book book)  {
        bookRepository.save(book);
    }

    @Override
    public Book getById(long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book getNewBook() throws EmptyFieldException {
        ioService.write("Enter title of book");
        String title = ioService.read();
        ioService.write("Enter the author (or authors - separated by commas)");
        List<String> authorNames = Arrays.asList(ioService.read().split(","));
        ioService.write("Enter a genre (or genres - separated by commas)");
        List<String> genreNames = Arrays.asList(ioService.read().split(","));

        HashSet<Author> authors = new HashSet<>();
        authorNames.forEach(name -> authors.add(authorService.findByName(name.trim())));
        HashSet<Genre> genres = new HashSet<>();
        genreNames.forEach(name -> genres.add(genreService.findByName(name.trim())));
        return Book.builder()
                .title(title)
                .authors(authors)
                .genres(genres)
                .build();
    }

    @Override
    public Set<Book> findByAuthorName(String name) {
        return authorService.findByName(name).getBooks();
    }

}