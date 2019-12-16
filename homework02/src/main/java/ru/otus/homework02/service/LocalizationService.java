package ru.otus.homework02.service;

public interface LocalizationService {

    void outputTextForEnterCorrectAnswer();

    void outputTextForRangeWarningMessage(int maxAnswerNumber);

    void outputTextForExceptionEerrorMessage(int maxAnswerNumber);

    void outputTextForEnterFirstName();

    void outputTextForEnterLastName();

    void outputTextForResultMessage(String studentFullName, int correctAnswers, int questionsNumber);

}
