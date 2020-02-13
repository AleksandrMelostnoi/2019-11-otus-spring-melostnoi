package ru.otus.homework04.dao;

import ru.otus.homework04.exception.QuizDataFormatException;
import ru.otus.homework04.model.Question;

import java.util.List;

public interface QuizDao {

    List<Question> readQuizzes() throws QuizDataFormatException;

}
