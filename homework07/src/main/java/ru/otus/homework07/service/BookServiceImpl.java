package ru.otus.homework07.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework07.Exception.EmptyFieldException;
import ru.otus.homework07.repository.BookRepository;
import ru.otus.homework07.entity.Book;

import java.util.List;
import java.util.stream.Collectors;

import static ru.otus.homework07.util.Util.validateEmptyField;

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

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public long getCount() {
        return bookRepository.count();
    }

    @Override
    public Book save(Book book) throws EmptyFieldException {
        if (!validateEmptyField(book).isEmpty()){
            throw new EmptyFieldException(" Ошибка! У книги отсутствует поле " + validateEmptyField(book));
        }
        return bookRepository.save(book);
    }

    @Override
    public Book findById(long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book getNewBook() {
        ioService.write("Введите название книги");
        String title = ioService.read();
        ioService.write("Введите автора");
        String authorName = ioService.read();
        ioService.write("Введите жанр");
        String genreName = ioService.read();
        return new Book(title, genreService.findByName(genreName), authorService.findByName(authorName));
    }

    @Override
    public List<Book> findByAuthorName(String name) {
        return findAll().stream()
                .filter(book -> book.getAuthor().getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

}