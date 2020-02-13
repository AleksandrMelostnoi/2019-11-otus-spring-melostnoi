package ru.otus.homework04.service;

public interface LocalizationService {

    String outputLocalizedMsgForEnterCorrectAnswer();

    void outputLocalizedMsgForRangeWarningMessage(int maxAnswerNumber);

    void outputLocalizedMsgForExceptionErrorMessage(int maxAnswerNumber);

    void outputLocalizedMsgForEnterFirstName();

    void outputLocalizedMsgForEnterLastName();

    void outputLocalizedMsgForResultMessage(String studentFullName, int correctAnswers, int questionsNumber);

}
