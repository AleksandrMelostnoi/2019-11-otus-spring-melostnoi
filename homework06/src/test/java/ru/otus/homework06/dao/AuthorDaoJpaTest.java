package ru.otus.homework06.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework06.entity.Author;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import(AuthorDaoImpl.class)
class AuthorDaoJpaTest {

    private final String EXIST_AUTHOR_NAME = "Стивен Кинг";
    private final String NEW_AUTHOR_NAME = "Пушкин";
    private final String WRONG_AUTHOR_NAME = "Есенин";

    @Autowired
    private AuthorDao authorRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void findByNameTest() {
        Optional<Author> author = authorRepository.getByName(EXIST_AUTHOR_NAME);
        assertTrue(author.isPresent());
        assertEquals(1, author.get().getId());
    }

    @Test
    void findByNameTestMustFail() {
        Optional<Author> author = authorRepository.getByName(WRONG_AUTHOR_NAME);
        assertFalse(author.isPresent());
    }

    @Test
    void insertTest() {
        Author author = new Author();
        author.setName(NEW_AUTHOR_NAME);
        authorRepository.insert(author);
        entityManager.detach(author);
        Author authorFromDb = entityManager.find(Author.class, author.getId());
        assertEquals(author, authorFromDb);
    }


}
