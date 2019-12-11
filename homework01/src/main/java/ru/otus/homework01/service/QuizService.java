package ru.otus.homework01.service;

import ru.otus.homework01.exception.QuizDataFormatException;

public interface QuizService {

    void startQuiz() throws QuizDataFormatException;

}
