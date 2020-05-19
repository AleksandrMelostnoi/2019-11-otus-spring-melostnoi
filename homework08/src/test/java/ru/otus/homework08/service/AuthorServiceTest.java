package ru.otus.homework08.service;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.homework08.entity.Author;
import ru.otus.homework08.entity.Book;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    private static final String EXIST_AUTHOR_NAME = "Михаил Лермонтов";
    private static final String NEW_AUTHOR_NAME = "Лев Толстой";

    @Autowired
    private MongoOperations mongoOperations;

    @BeforeEach
    void setUp() {
        mongoOperations.dropCollection(Author.class);
    }

    @Test
    void createAuthorTest() {
        Author author = authorService.saveAuthor(NEW_AUTHOR_NAME);
        Author result = mongoOperations.findById(author.getId(), Author.class);
        assertThat(result).isNotNull();
    }

    @Test
    void findAuthorsByBookIdTest() {
        Author author = new Author(EXIST_AUTHOR_NAME);
        mongoOperations.insert(author);
        Book book = Book.builder()
                .title("test book name")
                .authors(Set.of(author))
                .build();
        mongoOperations.insert(book);
        mongoOperations.save(author);
        Set<Author> results = authorService.findAuthorsByBookId(book.getId());
        SoftAssertions.assertSoftly(softAssertions -> softAssertions.assertThat(results).isNotEmpty());
    }

}
