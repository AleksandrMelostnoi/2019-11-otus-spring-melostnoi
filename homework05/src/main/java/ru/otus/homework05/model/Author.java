package ru.otus.homework05.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class Author {

    private long id;
    private String firstName;
    private String middleName;
    private String lastName;

}