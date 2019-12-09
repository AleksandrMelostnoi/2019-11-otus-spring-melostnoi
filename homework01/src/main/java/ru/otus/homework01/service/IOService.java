package ru.otus.homework01.service;

public interface IOService {

    String inputFirstName();

    String inputLastName();

    void printEnterFirstName();

    void printEnterLastName();

    void printNumberInputWarningMessage(int number);

    void printEnterAnswerNumberMessage();

    int inputAnswerNumber();

    void printQuestion(String questionText);

    void printResult(String result);

}
