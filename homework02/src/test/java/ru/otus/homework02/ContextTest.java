package ru.otus.homework02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@DisplayName("Tests for context")
class ContextTest {

    @Test
    void testGetContext() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        assertNotNull(context);
    }

}
