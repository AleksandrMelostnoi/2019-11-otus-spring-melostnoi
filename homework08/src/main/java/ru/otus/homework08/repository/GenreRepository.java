package ru.otus.homework08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.homework08.entity.Genre;

import java.util.Optional;

@Repository
public interface GenreRepository extends MongoRepository<Genre, String> {

    Optional<Genre> findByName(String name);

}
