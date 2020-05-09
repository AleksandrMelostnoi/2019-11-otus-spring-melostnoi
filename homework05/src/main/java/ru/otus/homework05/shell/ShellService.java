package ru.otus.homework05.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.homework05.Exception.BookAlreadyExistsException;
import ru.otus.homework05.Exception.EmptyFieldException;
import ru.otus.homework05.model.Book;
import ru.otus.homework05.service.BookService;
import ru.otus.homework05.service.IOService;

import java.util.List;

@ShellComponent
public class ShellService {
    private final IOService ioService;
    private final BookService bookService;

    @Autowired
    public ShellService(BookService bookService, IOService ioService) {
        this.bookService = bookService;
        this.ioService = ioService;
    }

    @ShellMethod(key = {"add"}, value = "add book to library")
    public void addBook() throws BookAlreadyExistsException, EmptyFieldException {
        Book book = bookService.getNewBook();
        bookService.insert(book);
    }

    @ShellMethod(key = {"get_id"}, value = "get book by Id")
    public void getBookById() {
        long id = ioService.readInt();
        ioService.write(bookService.getById(id).toString());
    }

    @ShellMethod(key = {"all"}, value = "show all books")
    public void allBooks() {
        List<Book> allBooks = bookService.getAll();
        allBooks.forEach(book -> ioService.write(book.toString()));
    }

    @ShellMethod(key = "count", value = "count of all books")
    public void bookCount() {
        ioService.write(bookService.getCount());
    }

    @ShellMethod(key = {"del_id"}, value = "delete book by Id")
    public void deleteBookById() {
        long id = ioService.readInt();
        bookService.deleteById(id);
    }

}
