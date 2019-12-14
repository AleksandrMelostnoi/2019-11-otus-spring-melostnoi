package ru.otus.homework02.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestingResult {

    private Student student;
    private int numberOfCorrectAnswers;
    private int questionsNumber;

    public String getFullName() {
        return student.getFirstName() + " " + student.getLastName();
    }

}
