package ru.otus.homework08.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.homework08.entity.Author;
import ru.otus.homework08.entity.Book;
import ru.otus.homework08.entity.Genre;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Tests book service")
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    private static final String BOOK_NAME = "Мцыри";
    private static final String AUTHOR_NAME = "Михаил Лермонтов";
    private static final String GENRE_NAME = "Русская классика";

    @Autowired
    private BookServiceImpl bookService;

    @Test
    void shouldReturnListAllBooks() {
        List<Book> listBook = List.of(getDefaultBook());
        when(bookService.findAll()).thenReturn(listBook);
        List<Book> actual = bookService.findAll();
        assertThat(listBook).hasSameSizeAs(actual)
                .hasSameElementsAs(actual);
    }

    private Book getDefaultBook() {
        Set<Author> authors = Set.of(new Author(AUTHOR_NAME));
        Set<Genre> genres = Set.of(new Genre(GENRE_NAME));
        return new Book(BOOK_NAME, genres, authors);
    }

}
