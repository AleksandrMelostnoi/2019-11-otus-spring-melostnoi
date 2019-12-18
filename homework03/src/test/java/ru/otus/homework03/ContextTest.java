package ru.otus.homework03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootTest
@DisplayName("Tests for context")
class ContextTest {

    @Test
    void testGetContext() {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        assertNotNull(context);
    }

}
