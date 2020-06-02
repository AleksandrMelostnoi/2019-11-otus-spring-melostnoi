package ru.otus.homework08.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.homework08.entity.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.homework08.config", "ru.otus.homework08.repositories"})
public class GenreRepositoryTest {

    private final String EXIST_GENRE_NAME = "Русская классика";
    private final String NEW_GENRE_NAME = "Сказка";
    private final String WRONG_GENRE_NAME = "Поэма";

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void findByNameTest() {
        Genre genreExpected = mongoTemplate.findOne(query(where("name").is(EXIST_GENRE_NAME)), Genre.class);
        Optional<Genre> genreActual = genreRepository.findByName(EXIST_GENRE_NAME);
        assertThat(genreActual)
                .isPresent()
                .get().isEqualTo(genreExpected);
    }

    @Test
    void findByWrongNameTestMustFail() {
        Optional<Genre> genre = genreRepository.findByName(WRONG_GENRE_NAME);
        assertFalse(genre.isPresent());
    }

}
