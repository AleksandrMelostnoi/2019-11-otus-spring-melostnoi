package ru.otus.homework08.changelog;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.homework08.entity.Author;
import ru.otus.homework08.entity.Book;
import ru.otus.homework08.entity.Genre;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static ru.otus.homework08.changelog.Author.ALEKSANDR_PUSHKIN;
import static ru.otus.homework08.changelog.Author.DMITRIY_KORTAVA;
import static ru.otus.homework08.changelog.Author.EDUARD_BEZUGLOV;
import static ru.otus.homework08.changelog.Author.GARRY_GARRISON;
import static ru.otus.homework08.changelog.Author.ILF_PETROV;
import static ru.otus.homework08.changelog.Author.IVAN_KRYLOV;
import static ru.otus.homework08.changelog.Author.MIGHAIL_LERMONTOV;
import static ru.otus.homework08.changelog.Author.MIKHAIL_BULGAKOV;
import static ru.otus.homework08.changelog.Genre.DRAMA;
import static ru.otus.homework08.changelog.Genre.FABLE;
import static ru.otus.homework08.changelog.Genre.FANTASY;
import static ru.otus.homework08.changelog.Genre.HUMOR;
import static ru.otus.homework08.changelog.Genre.LEARN_BOOK;
import static ru.otus.homework08.changelog.Genre.REVIEW;
import static ru.otus.homework08.changelog.Genre.RUSSIAN_CLASSIC;
import static ru.otus.homework08.changelog.Genre.WORLD_CLASSIC;

@ChangeLog(order = "001")
public class DatabaseChangelog {

    private Map<String, Author> authors = new HashMap<>();
    private Map<String, Genre> genres = new HashMap<>();
    private Map<String, Book> books = new HashMap<>();

    @ChangeSet(order = "001", id = "dropDB", author = "amelostnoy", runAlways = true)
    public void dropDB(MongoDatabase database){
        database.drop();
    }

    @ChangeSet(order = "002", id = "addAuthors", author = "amelostnoy", runAlways = true)
    public void initAuthors(MongoTemplate template){
        List<String> authorNames = Arrays.asList(
                MIGHAIL_LERMONTOV,
                IVAN_KRYLOV,
                ILF_PETROV,
                MIKHAIL_BULGAKOV,
                ALEKSANDR_PUSHKIN,
                GARRY_GARRISON,
                EDUARD_BEZUGLOV,
                DMITRIY_KORTAVA);
        for (String authorName : authorNames) {
            Author author = new Author(authorName);
            template.save(author);
            authors.put(authorName, author);
        }
    }

    @ChangeSet(order = "003", id = "addGenres", author = "amelostnoy", runAlways = true)
    public void initGenres(MongoTemplate template){
        List<String> genreNames = Arrays.asList(
                FABLE,
                HUMOR,
                FANTASY,
                RUSSIAN_CLASSIC,
                WORLD_CLASSIC,
                DRAMA,
                LEARN_BOOK,
                REVIEW);
        for (String genreName : genreNames) {
            Genre genre = new Genre(genreName);
            template.save(genre);
            genres.put(genreName, genre);
        }
    }

    @ChangeSet(order = "004", id = "addBooks", author = "amelostnoy", runAlways = true)
    public void initBooks(MongoTemplate template){
        Genre genreFable = genres.get(FABLE);
        Genre genreHumor = genres.get(HUMOR);
        Genre genreFantasy = genres.get(FANTASY);
        Genre genreRussianClassic = genres.get(RUSSIAN_CLASSIC);
        Genre genreWorldClassic = genres.get(WORLD_CLASSIC);
        Genre genreDrama = genres.get(DRAMA);
        Genre genreLearn = genres.get(LEARN_BOOK);
        Genre genreReview = genres.get(REVIEW);

        Author authorIvanKrylov = authors.get(IVAN_KRYLOV);
        Author authorIlfPetrov = authors.get(ILF_PETROV);
        Author authorGarryGarrison = authors.get(GARRY_GARRISON);
        Author authorLermontov = authors.get(MIGHAIL_LERMONTOV);
        Author authorPushkin = authors.get(ALEKSANDR_PUSHKIN);
        Author authorBulgakov = authors.get(MIKHAIL_BULGAKOV);
        Author authorBezuglov = authors.get(EDUARD_BEZUGLOV);
        Author authorKortava = authors.get(DMITRIY_KORTAVA);

        List<Book> bookList = Arrays.asList(
                new Book("Мартышка и очки", Set.of(genreFable, genreHumor), Set.of(authorIvanKrylov)),
                new Book("Двенадцать стульев", Set.of(genreHumor), Set.of(authorIlfPetrov)),
                new Book("Билл - герой Галактики", Set.of(genreFantasy), Set.of(authorGarryGarrison)),
                new Book("Мцыри", Set.of(genreRussianClassic), Set.of(authorLermontov)),
                new Book("Сказка о рыбаке и рыбки", Set.of(genreWorldClassic, genreRussianClassic), Set.of(authorPushkin)),
                new Book("Про футбол. Больше, чем спорт", Set.of(genreReview, genreLearn), Set.of(authorBezuglov, authorKortava)),
                new Book("Мастер и маргарита", Set.of(genreDrama), Set.of(authorBulgakov)));
        for (Book book : bookList) {
            template.save(book);
            books.put(book.getTitle(), book);
        }
    }

}
