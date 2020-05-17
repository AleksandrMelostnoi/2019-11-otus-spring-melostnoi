package ru.otus.homework06.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework06.Exception.EmptyFieldException;
import ru.otus.homework06.dao.BookDao;
import ru.otus.homework06.entity.Author;
import ru.otus.homework06.entity.Book;
import ru.otus.homework06.entity.Genre;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {
    final private IOService ioService;
    final private BookDao bookDao;
    final private GenreService genreService;
    final private AuthorService authorService;

    @Autowired
    public BookServiceImpl(IOService ioService, BookDao bookDao, GenreService genreService, AuthorService authorService) {
        this.ioService = ioService;
        this.bookDao = bookDao;
        this.genreService = genreService;
        this.authorService = authorService;
    }

    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public long getCount() {
        return bookDao.getCount();
    }

    @Override
    public void insert(Book book)  {
        bookDao.insert(book);
    }

    @Override
    public Book getById(long id) {
        return bookDao.getById(id);
    }

    @Override
    public void deleteById(long id) {
        bookDao.deleteById(id);
    }

    @Override
    public Book getNewBook() throws EmptyFieldException {
        ioService.write("Введите название книги");
        String title = ioService.read();
        ioService.write("Введите автора (авторов - через запятую)");
        List<String> authorNames = Arrays.asList(ioService.read().split(","));
        ioService.write("Введите жанр (жанры - через запятую");
        List<String> genreNames = Arrays.asList(ioService.read().split(","));

        HashSet<Author> authors = new HashSet<>();
        authorNames.forEach(name -> authors.add(authorService.getAuthor(name.trim())));
        HashSet<Genre> genres = new HashSet<>();
        genreNames.forEach(name -> genres.add(genreService.getGenre(name.trim())));
        return Book.builder()
                .title(title)
                .authors(authors)
                .genres(genres)
                .build();
    }

    @Override
    public Set<Book> findByAuthorName(String name) {
        return authorService.getAuthor(name).getBooks();
    }

}