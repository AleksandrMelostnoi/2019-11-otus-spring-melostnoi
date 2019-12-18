package ru.otus.homework02.dao;

import ru.otus.homework02.exception.QuizDataFormatException;
import ru.otus.homework02.model.Question;

import java.util.List;

public interface QuizDao {

    List<Question> readQuizzes() throws QuizDataFormatException;

}
