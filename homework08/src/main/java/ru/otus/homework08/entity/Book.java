package ru.otus.homework08.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "books")
public class Book {

    @Id
    private String id;
    private String title;
    private Set<Author> authors = new HashSet<>();
    private Set<Genre> genres = new HashSet<>();

    public Book(String title, Set<Genre> genres, Set<Author> authors) {
        this.title = title;
        this.genres = genres;
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", genres=" + genres +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) &&
                Objects.equals(authors, book.authors) &&
                Objects.equals(genres, book.genres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, authors, genres);
    }

}
