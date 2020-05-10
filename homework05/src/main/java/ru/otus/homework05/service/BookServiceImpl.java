package ru.otus.homework05.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework05.Exception.BookAlreadyExistsException;
import ru.otus.homework05.Exception.EmptyFieldException;
import ru.otus.homework05.dao.BookDao;
import ru.otus.homework05.model.Book;

import java.util.List;

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
    public void insert(Book book) throws BookAlreadyExistsException, EmptyFieldException {
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
    public Book getNewBook() {
        ioService.write("Введите название книги");
        String title = ioService.read();
        ioService.write("Введите автора");
        String authorName = ioService.read();
        ioService.write("Введите жанр");
        String genreName = ioService.read();
        return new Book(title, genreService.getGenre(genreName), authorService.getAuthor(authorName));
    }
}