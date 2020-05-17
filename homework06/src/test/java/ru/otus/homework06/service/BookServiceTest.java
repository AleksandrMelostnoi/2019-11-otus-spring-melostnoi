package ru.otus.homework06.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.homework06.Exception.EmptyFieldException;
import ru.otus.homework06.dao.BookDao;
import ru.otus.homework06.entity.Author;
import ru.otus.homework06.entity.Book;
import ru.otus.homework06.entity.Genre;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@DisplayName("Tests book service")
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@ExtendWith(SpringExtension.class)
public class BookServiceTest {

    private static final Long GENRE_ID = 2L;
    private static final Long AUTHOR_ID = 2L;
    private static final Long BOOK_ID = 2L;

    private static final String BOOK_NAME = "Tom Sawyer";
    private static final String AUTHOR_NAME = "Mark Twain";
    private static final String GENRE_NAME = "Novel";

    @Autowired
    private BookServiceImpl bookService;

    @MockBean
    private BookDao bookDao;

    @MockBean
    private GenreService genreService;

    @MockBean
    private AuthorService authorService;

    @Test
    @DisplayName("add book with book service Test")
    void shouldCorrectInsertBook() throws EmptyFieldException {
        Mockito.when(authorService.getAuthor(anyString())).thenReturn(new Author(AUTHOR_ID, AUTHOR_NAME));
        Mockito.when(genreService.getGenre(any())).thenReturn(new Genre(GENRE_ID, GENRE_NAME));

        HashSet<Author> authors = new HashSet<>();
        authors.add(authorService.getAuthor(AUTHOR_NAME));
        HashSet<Genre> genres = new HashSet<>();
        genres.add(genreService.getGenre(GENRE_NAME));

        bookService.insert(new Book(BOOK_ID, BOOK_NAME, authors, genres));

        ArgumentCaptor<Book> argument = ArgumentCaptor.forClass(Book.class);
        verify(bookDao, Mockito.times(1)).insert(argument.capture());
        assertEquals(BOOK_NAME, argument.getValue().getTitle());
        assertEquals(AUTHOR_ID, argument.getValue().getAuthors().stream().findFirst().get().getId());
        assertEquals(GENRE_ID, argument.getValue().getGenres().stream().findFirst().get().getId());
    }

}
