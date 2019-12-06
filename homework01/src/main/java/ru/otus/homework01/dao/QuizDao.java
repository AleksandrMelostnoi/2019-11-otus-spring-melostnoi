package ru.otus.homework01.dao;

import ru.otus.homework01.model.QuizRow;

import java.util.List;

public interface QuizDao {

    List<QuizRow> readQuizzes();

}
