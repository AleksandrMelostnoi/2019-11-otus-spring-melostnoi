package ru.otus.homework07.service;

import ru.otus.homework07.Exception.EmptyFieldException;

public interface IOService {

    String read() throws EmptyFieldException;
    Integer readInt();
    void write(long count);
    void write(Object obj);

}
