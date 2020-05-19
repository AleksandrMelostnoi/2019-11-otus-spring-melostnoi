package ru.otus.homework08.changelog;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.homework08.entity.Author;
import ru.otus.homework08.entity.Book;
import ru.otus.homework08.entity.Genre;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@ChangeLog(order = "001")
public class DatabaseChangelog {

    private String MIGHAIL_LERMONTOV = "Михаил Лермонтов";
    private String RUSSIAN_CLASSIC = "Русская классика";
    private Map<String, Author> authors = new HashMap<>();
    private Map<String, Genre> genres = new HashMap<>();
    private Map<String, Book> books = new HashMap<>();

    @ChangeSet(order = "001", id = "dropDB", author = "amelostnoy", runAlways = true)
    public void dropDB(MongoDatabase database){
        database.drop();
    }

    @ChangeSet(order = "002", id = "addAuthors", author = "amelostnoy", runAlways = true)
    public void initAuthors(MongoTemplate template) {
        List<String> authorNames = Collections.singletonList(MIGHAIL_LERMONTOV);
        for (String authorName : authorNames) {
            Author author = new Author(authorName);
            template.save(author);
            authors.put(authorName, author);
        }
    }

    @ChangeSet(order = "003", id = "addGenres", author = "amelostnoy", runAlways = true)
    public void initGenres(MongoTemplate template) {
        List<String> genreNames = Collections.singletonList(RUSSIAN_CLASSIC);
        for (String genreName : genreNames) {
            Genre genre = new Genre(genreName);
            template.save(genre);
            genres.put(genreName, genre);
        }
    }

    @ChangeSet(order = "004", id = "addBooks", author = "amelostnoy", runAlways = true)
    public void initBooks(MongoTemplate template) {
        Genre genreRussianClassic = genres.get(RUSSIAN_CLASSIC);
        Author authorLermontov = authors.get(MIGHAIL_LERMONTOV);

        List<Book> bookList = Collections.singletonList(
                new Book("Мцыри", Set.of(genreRussianClassic), Set.of(authorLermontov)));
        for (Book book : bookList) {
            template.save(book);
            books.put(book.getTitle(), book);
        }
    }

}
