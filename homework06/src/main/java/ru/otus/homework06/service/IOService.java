package ru.otus.homework06.service;

import ru.otus.homework06.Exception.EmptyFieldException;

public interface IOService {

    String read() throws EmptyFieldException;
    Integer readInt();
    void write(long count);
    void write(Object obj);

}
