package ru.otus.homework06.util;

import ru.otus.homework06.entity.Book;

public class Util {

    public static String validateEmptyField(Book book) {
        if (book.getTitle().isEmpty()) {
            return "title";
        } else if (book.getAuthor().getName().isEmpty()) {
            return "author";
        } else if (book.getGenre().getName().isEmpty()) {
            return "genre";
        }
        return "";
    }

}
