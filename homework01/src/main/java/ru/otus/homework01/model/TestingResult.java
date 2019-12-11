package ru.otus.homework01.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestingResult {

    private Student student;
    private int numberOfCorrectAnswers;
    private int questionsNumber;

    public String convertTestData2String() {
        return "Dear " + student.getFirstName() + " " + student.getLastName() + "!" + "\n" +
                "Number of correct answers: " + numberOfCorrectAnswers + " out of " + questionsNumber + "\n";
    }

}
