package ru.otus.homework02.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Answer {

    private int id;
    private String answerText;
    private Boolean type;

}
