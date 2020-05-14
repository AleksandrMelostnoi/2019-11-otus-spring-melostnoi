package ru.otus.homework07.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework07.Exception.EmptyFieldException;
import ru.otus.homework07.entity.Book;
import ru.otus.homework07.service.BookService;
import ru.otus.homework07.service.IOService;

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

    @Transactional
    @ShellMethod(key = {"add"}, value = "add book to library")
    public void addBook() throws EmptyFieldException {
        bookService.save(bookService.getNewBook());
    }

    @ShellMethod(key = {"get_id"}, value = "get book by Id")
    public void getBookById() {
        ioService.write(bookService.findById(ioService.readInt()).toString());
    }

    @ShellMethod(key = {"all"}, value = "show all books")
    public void allBooks() {
        List<Book> allBooks = bookService.findAll();
        allBooks.forEach(book -> ioService.write(book.toString()));
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
    public void getBooksByAuthor() {
        ioService.write("Enter author");
        List<Book> booksByAuthorName = bookService.findByAuthorName(ioService.read());
        if (booksByAuthorName.isEmpty()) {
            ioService.write("Books not found");
        }
        booksByAuthorName.forEach(book -> ioService.write(book.toString()));
    }

}
