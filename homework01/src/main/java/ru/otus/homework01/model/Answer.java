package ru.otus.homework01.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Answer {

    private int id;
    private String answerText;
    private Boolean type;

}
