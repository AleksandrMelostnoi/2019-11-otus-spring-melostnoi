package ru.otus.homework08.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Data
@RequiredArgsConstructor
@Document(collection = "authors")
public class Author {

    @Id
    private String id;
    private String name;

    public Author(String authorName) {
        this.name = authorName;
    }

}
