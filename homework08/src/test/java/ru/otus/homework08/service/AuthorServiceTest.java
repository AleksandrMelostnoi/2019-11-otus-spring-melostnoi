package ru.otus.homework08.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.homework08.entity.Author;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    private static final String AUTHOR_NAME = "Михаил Лермонтов";

    @Test
    @DisplayName("find author by name Test")
    void findAuthorByName() {
        Optional<Author> author = authorService.findByName(AUTHOR_NAME);
        assertThat(author).isPresent();
        assertEquals(AUTHOR_NAME, author.get().getName());
    }

}
