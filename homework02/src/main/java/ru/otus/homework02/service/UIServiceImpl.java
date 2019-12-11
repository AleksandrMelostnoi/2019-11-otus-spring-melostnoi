package ru.otus.homework02.service;


import ru.otus.homework02.model.Student;
import ru.otus.homework02.model.TestingResult;

import java.util.InputMismatchException;

public class UIServiceImpl implements UIService {

    private final IOService ioService;

    public UIServiceImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public int getUserAnswer(int maxAnswerNumber) {
        ioService.outputText("Enter number of correct answer: ");
        Integer answerNumber = null;
        try {
            answerNumber = ioService.inputNumber();
            if (1 > answerNumber || answerNumber > maxAnswerNumber) {
                ioService.outputText("Only number less or equals " + maxAnswerNumber + "\n");
                return 0;
            }
        } catch (InputMismatchException e) {
            ioService.outputText("Only number from 1 to " + maxAnswerNumber + "\n");
            return 0;
        }
        return answerNumber;
    }

    @Override
    public Student readStudentInfo() {
        Student student = new Student();
        ioService.outputText("Please input first name");
        student.setFirstName(ioService.inputText());
        ioService.outputText("Please input last name");
        student.setLastName(ioService.inputText());
        return student;
    }

    @Override
    public void printQuestion(int questionNumber, String questionText) {
        ioService.outputText("\n" + "Question " + questionNumber + ". " + questionText);
    }

    @Override
    public void printResult(TestingResult testingResult) {
        ioService.outputText(testingResult.convertTestData2String());
    }

}
