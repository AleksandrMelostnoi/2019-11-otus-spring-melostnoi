package ru.otus.homework01.model;

import lombok.Data;

import java.util.List;

@Data
public class QuizRow {

    private Question question;
    private List<Answer> answerList;

}
