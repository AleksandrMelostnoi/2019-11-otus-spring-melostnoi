package ru.otus.homework01.dao;

import ru.otus.homework01.exception.QuizDataFormatException;
import ru.otus.homework01.model.Question;

import java.util.List;

public interface QuizDao {

    List<Question> readQuizzes() throws QuizDataFormatException;

}
