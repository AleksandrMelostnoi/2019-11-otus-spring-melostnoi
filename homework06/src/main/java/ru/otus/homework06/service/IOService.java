package ru.otus.homework06.service;

public interface IOService {

    String read();
    Integer readInt();
    void write(long count);
    void write(String text);

}
