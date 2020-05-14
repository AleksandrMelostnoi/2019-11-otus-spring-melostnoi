package ru.otus.homework07.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.homework07.entity.Author;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class AuthorRepositoryTest {

    private final String EXIST_AUTHOR_NAME = "Стивен Кинг";
    private final String NEW_AUTHOR_NAME = "Пушкин";
    private final String WRONG_AUTHOR_NAME = "Есенин";

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void findByNameTest() {
        Optional<Author> author = authorRepository.findByName(EXIST_AUTHOR_NAME);
        assertTrue(author.isPresent());
        assertEquals(1, author.get().getId());
    }

    @Test
    void findByWrongNameTestMustFail() {
        Optional<Author> author = authorRepository.findByName(WRONG_AUTHOR_NAME);
        assertFalse(author.isPresent());
    }

    @Test
    void saveAuthorTest() {
        Author author = new Author();
        author.setName(NEW_AUTHOR_NAME);
        authorRepository.save(author);
        entityManager.detach(author);
        Author authorFromDB = entityManager.find(Author.class, author.getId());
        assertEquals(author, authorFromDB);
    }

}
