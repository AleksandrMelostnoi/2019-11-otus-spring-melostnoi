package ru.otus.homework03.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Answer {

    private int id;
    private String answerText;
    private boolean type;

}
