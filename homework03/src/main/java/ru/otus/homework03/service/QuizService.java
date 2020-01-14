package ru.otus.homework03.service;

import ru.otus.homework03.exception.QuizDataFormatException;

public interface QuizService {

    void startQuiz() throws QuizDataFormatException;

}
