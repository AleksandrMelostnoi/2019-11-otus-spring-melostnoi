package ru.otus.homework08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.homework08.entity.Author;

import java.util.Optional;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {

    Optional<Author> findByName(String name);

}
