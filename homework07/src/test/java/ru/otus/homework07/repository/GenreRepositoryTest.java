package ru.otus.homework07.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.homework07.entity.Genre;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class GenreRepositoryTest {

    private final String EXIST_GENRE_NAME = "Ужасы";
    private final String NEW_GENRE_NAME = "Сказка";
    private final String WRONG_GENRE_NAME = "Поэма";

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void findByNameTest() {
        Optional<Genre> genre = genreRepository.findByName(EXIST_GENRE_NAME);
        assertTrue(genre.isPresent());
        assertEquals(1, genre.get().getId());
    }

    @Test
    void findByWrongNameTestMustFail() {
        Optional<Genre> genre = genreRepository.findByName(WRONG_GENRE_NAME);
        assertFalse(genre.isPresent());
    }

    @Test
    void saveGenreTest() {
        Genre genre = new Genre();
        genre.setName(NEW_GENRE_NAME);
        genreRepository.save(genre);
        entityManager.detach(genre);
        Genre genreFromDB = entityManager.find(Genre.class, genre.getId());
        assertEquals(genre, genreFromDB);
    }

}
