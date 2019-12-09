package ru.otus.homework01.service;

import ru.otus.homework01.model.Student;

import java.util.InputMismatchException;

public class UIServiceImpl implements UIService {

    private final IOService ioService;

    public UIServiceImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public Integer getUserAnswer(int maxAnswerNumber) {
        ioService.printEnterAnswerNumberMessage();
        Integer answerNumber = null;
        try {
            answerNumber = ioService.inputAnswerNumber();
            if (1 > answerNumber || answerNumber > maxAnswerNumber) {
                ioService.printNumberInputWarningMessage(maxAnswerNumber);
                return 0;
            }
        } catch (InputMismatchException e) {
            ioService.printNumberInputWarningMessage(maxAnswerNumber);
            return 0;
        }
        return answerNumber;
    }

    public void readStudentName(Student student) {
        ioService.printEnterFirstName();
        student.setFirstName(ioService.inputFirstName());
        ioService.printEnterLastName();
        student.setLastName(ioService.inputLastName());
    }

    @Override
    public void printQuestion(int questionNumber, String questionText) {
        ioService.printQuestion("\n" + "Question " + questionNumber + ". " + questionText);
    }

    @Override
    public void printResult(Student student, int correctAnswers, int size) {
        ioService.printResult("Dear " + student.getFirstName() + " " + student.getLastName() + "!" + "\n" +
                "Number of correct answers: " + correctAnswers + " out of " + size + "\n");
    }

}
