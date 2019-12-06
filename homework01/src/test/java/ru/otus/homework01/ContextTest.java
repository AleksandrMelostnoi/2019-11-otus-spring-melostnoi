package ru.otus.homework01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@DisplayName("Tests for context")
class ContextTest {

    @Test
    void testGetContext() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context-test.xml");
        assertNotNull(context);
    }

}
