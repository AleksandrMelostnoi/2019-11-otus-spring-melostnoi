package ru.otus.homework08.service;

import ru.otus.homework08.Exception.EmptyFieldException;

public interface IOService {

    String read() throws EmptyFieldException;
    void write(long count);
    void write(Object obj);

}
