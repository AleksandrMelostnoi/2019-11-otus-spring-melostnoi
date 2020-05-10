package ru.otus.homework05.service;

public interface IOService {

    String read();
    Integer readInt();
    void write(long count);
    void write(String text);

}
