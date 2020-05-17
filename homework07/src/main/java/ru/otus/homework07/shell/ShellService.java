package ru.otus.homework07.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework07.Exception.EmptyFieldException;
import ru.otus.homework07.service.AuthorService;
import ru.otus.homework07.service.BookService;
import ru.otus.homework07.service.GenreService;
import ru.otus.homework07.service.IOService;

@ShellComponent
public class ShellService {
    private final IOService ioService;
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Autowired
    public ShellService(GenreService genreService, AuthorService authorService, BookService bookService, IOService ioService) {
        this.genreService = genreService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.ioService = ioService;
    }

    @Transactional
    @ShellMethod(key = {"add"}, value = "add book to library")
    public void addBook() throws EmptyFieldException {
        bookService.save(bookService.getNewBook());
    }

    @ShellMethod(key = {"getb_id"}, value = "get book by Id")
    public void getBookById() {
        ioService.write(bookService.getById(ioService.readInt()));
    }

    @ShellMethod(key = {"all_b"}, value = "show all books")
    public void allBooks() {
        bookService.findAll().forEach(ioService::write);
    }

    @ShellMethod(key = "count", value = "count of all books")
    public void bookCount() {
        ioService.write(bookService.getCount());
    }

    @Transactional
    @ShellMethod(key = {"del_id"}, value = "delete book by Id")
    public void deleteBookById() {
        bookService.deleteById(ioService.readInt());
    }

    @ShellMethod(key = {"get_a"}, value = "get books by Author")
    public void getBooksByAuthor() throws EmptyFieldException {
        bookService.findByAuthorName(ioService.read()).forEach(ioService::write);
    }

    @ShellMethod(key = {"all_a"}, value = "show all authors")
    public void allAuthors() {
        authorService.findAll().forEach(ioService::write);
    }

    @ShellMethod(key = {"all_g"}, value = "show all genres")
    public void allGenres() {
        genreService.findAll().forEach(ioService::write);
    }

}
