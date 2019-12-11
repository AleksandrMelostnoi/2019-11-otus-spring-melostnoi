package ru.otus.homework01.model;

import lombok.Data;

import java.util.List;

@Data
public class Question {

    private String questionText;
    private List<Answer> answerList;

}
