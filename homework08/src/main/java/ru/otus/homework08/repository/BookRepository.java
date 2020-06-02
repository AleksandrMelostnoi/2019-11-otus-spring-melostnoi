package ru.otus.homework08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.homework08.entity.Author;
import ru.otus.homework08.entity.Book;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findAllByAuthorsContaining(Author author);

}
