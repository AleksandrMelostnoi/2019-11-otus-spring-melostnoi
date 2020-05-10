package ru.otus.homework05.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.homework05.Exception.BookAlreadyExistsException;
import ru.otus.homework05.Exception.EmptyFieldException;
import ru.otus.homework05.model.Author;
import ru.otus.homework05.model.Book;
import ru.otus.homework05.model.Genre;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Tests book DAO")
@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(BookDaoImpl.class)
class BookDaoTest {

    private static final long EXPECTED_BOOKS_COUNT = 1L;

    private static final String NEW_BOOK_TITLE = "Кладбище домашних животных";
    private static final long NEW_BOOK_ID = 2L;
    private static final long GET_BOOK_ID = 1L;
    @Autowired
    private BookDaoImpl bookDao;

    @DisplayName("Get right books count Test")
    @Test
    void shouldReturnCorrectBookCount() {
        assertEquals(EXPECTED_BOOKS_COUNT, bookDao.getCount());
    }

    @DisplayName("Add book Test")
    @Test
    void shouldInsertBook() throws BookAlreadyExistsException, EmptyFieldException, SQLException {
        Book expectedBook = new Book(NEW_BOOK_ID, NEW_BOOK_TITLE, new Author(new Long(1),"Стивен Кинг"), new Genre(new Long(1),"Ужасы"));
        bookDao.insert(expectedBook);
        Book actualBook = bookDao.getById(NEW_BOOK_ID);
        assertEquals(expectedBook, actualBook);
    }

    @DisplayName("Get book by id Test")
    @Test
    void shouldReturnCorrectBookById() {
        Book expectedBook = new Book(GET_BOOK_ID, "Оно", new Author(new Long(1), "Стивен Кинг"), new Genre(new Long(1), "Ужасы"));
        Book actualBook = bookDao.getById(GET_BOOK_ID);
        assertEquals(expectedBook, actualBook);
    }

    @DisplayName("Get books list Test")
    @Test
    void shouldReturnCorrectBookList() {
        Book book = new Book(GET_BOOK_ID, "Оно", new Author(new Long(1), "Стивен Кинг"), new Genre(new Long(1), "Ужасы"));
        List<Book> expectedBooks = Arrays.asList(book);
        List<Book> actualBooks = bookDao.getAll();
        assertEquals(expectedBooks, actualBooks);
    }

}
