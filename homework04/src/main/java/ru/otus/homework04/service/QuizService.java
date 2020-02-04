package ru.otus.homework04.service;

import ru.otus.homework04.exception.QuizDataFormatException;

public interface QuizService {

    void startQuiz() throws QuizDataFormatException;

    void login();

    boolean isLogin();
}
