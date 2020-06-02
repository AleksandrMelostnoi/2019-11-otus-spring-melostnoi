package ru.otus.homework08.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.homework08.Exception.AuthorNotFoundException;
import ru.otus.homework08.Exception.EmptyFieldException;
import ru.otus.homework08.service.AuthorService;
import ru.otus.homework08.service.BookService;
import ru.otus.homework08.service.GenreService;
import ru.otus.homework08.service.IOService;

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

    @ShellMethod(key = {"add_b"}, value = "add book to library")
    public void addBook() throws EmptyFieldException {
        bookService.save(bookService.getNewBook());
    }

    @ShellMethod(key = {"getb_id"}, value = "get book by Id")
    public void getBookById() throws EmptyFieldException {
        ioService.write(bookService.findById(ioService.read()));
    }

    @ShellMethod(key = {"all_b"}, value = "show all books")
    public void allBooks() {
        bookService.findAll().forEach(ioService::write);
    }

    @ShellMethod(key = "count", value = "count of all books")
    public void bookCount() {
        ioService.write(bookService.getCount());
    }

    @ShellMethod(key = {"delb_id"}, value = "delete book by Id")
    public void deleteBookById() throws EmptyFieldException {
        bookService.deleteById(ioService.read());
    }

    @ShellMethod(key = {"get_a"}, value = "get author")
    public void getAuthor() throws EmptyFieldException {
        ioService.write(authorService.findByName(ioService.read()).get());
    }

    @ShellMethod(key = {"all_a"}, value = "show all authors")
    public void allAuthors() {
        authorService.findAll().forEach(ioService::write);
    }

    @ShellMethod(key = {"add_a"}, value = "add author")
    public void addAuthor() throws EmptyFieldException {
        authorService.saveAuthor(ioService.read());
    }

    @ShellMethod(key = {"dela_id"}, value = "delete author by Id")
    public void deleteAuthorById() throws EmptyFieldException {
        authorService.deleteById(ioService.read());
    }

    @ShellMethod(key = {"all_g"}, value = "show all genres")
    public void allGenres() {
        genreService.findAll().forEach(ioService::write);
    }

    @ShellMethod(key = {"add_g"}, value = "add genre")
    public void addGenre() throws EmptyFieldException {
        genreService.saveGenre(ioService.read());
    }

    @ShellMethod(key = {"get_g"}, value = "get genre")
    public void getGenre() throws EmptyFieldException {
        ioService.write(genreService.findByName(ioService.read()).get());
    }

    @ShellMethod(key = {"delg_id"}, value = "delete genre by Id")
    public void deleteGenreById() throws EmptyFieldException {
        genreService.deleteById(ioService.read());
    }

    @ShellMethod(key = {"getb_a"}, value = "get books by author")
    public void getBooksByAuthor() throws EmptyFieldException, AuthorNotFoundException {
        ioService.write(bookService.getBooksByAuthorName(ioService.read()));
    }

    @ShellMethod(key = {"geta_b"}, value = "get authors by book id")
    public void getAuthorsByBookId() throws EmptyFieldException {
        authorService.findAuthorsByBookId(ioService.read()).forEach(ioService::write);
    }

    @ShellMethod(key = {"dela_in_book"}, value = "delete author in book")
    public void deleteAuthorInBook() throws EmptyFieldException {
        ioService.write("Enter Book Id");
        String bookId = ioService.read();
        ioService.write("Enter Author Id");
        String authorId = ioService.read();
        bookService.deleteAuthor(bookId, authorId);
    }

}
