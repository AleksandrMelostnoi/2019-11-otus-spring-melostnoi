package ru.otus.homework08.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.homework08.entity.Author;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.homework08.config", "ru.otus.homework08.repositories"})
class AuthorRepositoryTest {

    private final String EXIST_AUTHOR_NAME = "Михаил Лермонтов";
    private final String NEW_AUTHOR_NAME = "Пушкин";
    private final String WRONG_AUTHOR_NAME = "Есенин";

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void shouldReturnListAllAuthorsTest() {
        List<Author> listExpected = mongoTemplate.findAll(Author.class);
        List<Author> listActual = authorRepository.findAll();
        assertThat(listActual)
                .hasSameSizeAs(listExpected)
                .hasSameElementsAs(listExpected);
    }

    @Test
    void findByNameTest() {
        Author authorExpected = mongoTemplate.findOne(query(where("name").is(EXIST_AUTHOR_NAME)), Author.class);
        Optional<Author> authorActual = authorRepository.findByName(EXIST_AUTHOR_NAME);
        assertThat(authorActual).isPresent()
                .get().isEqualTo(authorExpected);
    }

    @Test
    void findByWrongNameTestMustFail() {
        Optional<Author> author = authorRepository.findByName(WRONG_AUTHOR_NAME);
        assertTrue(author.isEmpty());
    }

}
