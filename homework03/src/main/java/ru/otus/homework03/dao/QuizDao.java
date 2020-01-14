package ru.otus.homework03.dao;

import ru.otus.homework03.exception.QuizDataFormatException;
import ru.otus.homework03.model.Question;

import java.util.List;

public interface QuizDao {

    List<Question> readQuizzes() throws QuizDataFormatException;

}
