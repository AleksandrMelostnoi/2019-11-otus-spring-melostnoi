package ru.otus.homework02.service;

import ru.otus.homework02.exception.QuizDataFormatException;

public interface QuizService {

    void startQuiz() throws QuizDataFormatException;

}
