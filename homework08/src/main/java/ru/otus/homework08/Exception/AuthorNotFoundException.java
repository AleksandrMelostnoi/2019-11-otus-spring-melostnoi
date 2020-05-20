package ru.otus.homework08.Exception;

public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException(String message) {
        super(String.format("Author '%s' not found!", message));
    }

}
