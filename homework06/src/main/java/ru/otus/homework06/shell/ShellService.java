package ru.otus.homework06.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.homework06.Exception.EmptyFieldException;
import ru.otus.homework06.entity.Book;
import ru.otus.homework06.service.BookService;
import ru.otus.homework06.service.IOService;

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
    public void addBook() throws EmptyFieldException {
        bookService.insert(bookService.getNewBook());
    }

    @ShellMethod(key = {"get_id"}, value = "get book by Id")
    public void getBookById() {
        ioService.write(bookService.getById(ioService.readInt()).toString());
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
        bookService.deleteById(ioService.readInt());
    }

}
