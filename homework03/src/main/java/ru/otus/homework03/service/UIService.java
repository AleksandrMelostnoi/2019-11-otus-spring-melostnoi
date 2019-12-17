package ru.otus.homework03.service;

import ru.otus.homework03.model.Student;
import ru.otus.homework03.model.TestingResult;

public interface UIService {

    int getUserAnswer(int maxAnswerNumber);

    Student readStudentInfo();

    void printQuestion(int questionNumber, String questionText);

    void printResult(TestingResult testingResult);

}
