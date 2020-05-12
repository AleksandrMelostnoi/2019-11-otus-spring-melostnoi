package ru.otus.homework06.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework06.Exception.EmptyFieldException;
import ru.otus.homework06.dao.BookDao;
import ru.otus.homework06.entity.Book;

import java.util.List;
import java.util.stream.Collectors;

import static ru.otus.homework06.util.Util.validateEmptyField;

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
    public void insert(Book book) throws EmptyFieldException {
        if (!validateEmptyField(book).isEmpty()){
            throw new EmptyFieldException(" Ошибка! У книги отсутствует поле " + validateEmptyField(book));
        }
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

    @Override
    public List<Book> findByAuthorName(String name) {
        return getAll().stream()
                .filter(book -> book.getAuthor().getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

}