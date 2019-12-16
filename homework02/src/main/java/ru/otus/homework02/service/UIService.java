package ru.otus.homework02.service;

import ru.otus.homework02.model.Student;
import ru.otus.homework02.model.TestingResult;

public interface UIService {

    int getUserAnswer(int maxAnswerNumber);

    Student readStudentInfo();

    void printQuestion(int questionNumber, String questionText);

    void printResult(TestingResult testingResult);

}
