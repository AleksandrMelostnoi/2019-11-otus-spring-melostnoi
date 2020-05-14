package ru.otus.homework07.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.homework07.entity.Author;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(AuthorServiceImpl.class)
@DataJpaTest
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    private static final String AUTHOR_NAME = "Mark Twain";

    @Test
    @DisplayName("find author by name Test")
    void shouldCorrectInsertBook() {
        Author author = authorService.findByName(AUTHOR_NAME);
        assertEquals(AUTHOR_NAME, author.getName());
    }

}
