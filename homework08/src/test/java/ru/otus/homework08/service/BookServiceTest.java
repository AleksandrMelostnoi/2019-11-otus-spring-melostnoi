package ru.otus.homework08.service;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.homework08.entity.Author;
import ru.otus.homework08.entity.Book;
import ru.otus.homework08.entity.Genre;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tests book service")
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class BookServiceTest {

    private static final String BOOK_TITLE = "Мцыри";
    private static final String NEW_BOOK_TITLE = "Парус";
    private static final String AUTHOR_NAME = "Михаил Лермонтов";
    private static final String GENRE_NAME = "Русская классика";

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private MongoOperations mongoOperations;

    @BeforeEach
    void setUp() {
        mongoOperations.dropCollection(Book.class);
    }

    @Test
    void createBookTest() {
        Book book = Book.builder().title(BOOK_TITLE).build();
        bookService.save(book);
        Book result = mongoOperations.findById(book.getId(), Book.class);
        assertThat(result).isNotNull();
    }

    @Test
    void updateTitleTest() {
        Book book = Book.builder().title(BOOK_TITLE).build();
        bookService.save(book);
        String updatedName = NEW_BOOK_TITLE;
        book.setTitle(updatedName);
        bookService.save(book);
        Book result = mongoOperations.findById(book.getId(), Book.class);
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(result).isNotNull();
            softAssertions.assertThat(result.getTitle()).isEqualTo(updatedName);
        });
    }

    @Test
    void deleteAuthorInBookTest() {
        Book book = Book.builder().title(BOOK_TITLE).build();
        mongoOperations.save(book);
        Author author = new Author(AUTHOR_NAME);
        mongoOperations.save(author);
        book.setAuthors(Set.of(author));
        mongoOperations.save(book);
        bookService.deleteAuthor(book.getId(), author.getId());
        Book result = mongoOperations.findById(book.getId(), Book.class);
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(result).isNotNull();
            softAssertions.assertThat(result.getAuthors()).isEmpty();
        });
    }

    @Test
    void addGenreTest() {
        Book book = Book.builder().title(BOOK_TITLE).build();
        mongoOperations.save(book);
        Genre genre = new Genre(GENRE_NAME);
        bookService.addGenre(book.getId(), genre);
        Book result = mongoOperations.findById(book.getId(), Book.class);
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(result).isNotNull();
            softAssertions.assertThat(result.getGenres()).isNotEmpty();
        });
    }

}
