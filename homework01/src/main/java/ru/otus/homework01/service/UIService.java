package ru.otus.homework01.service;

import ru.otus.homework01.model.Student;

public interface UIService {

    Integer getUserAnswer(int maxAnswerNumber);

    void readStudentName(Student student);

    void printQuestion(int questionNumber, String questionText);

    void printResult(Student student, int correctAnswers, int size);

}
