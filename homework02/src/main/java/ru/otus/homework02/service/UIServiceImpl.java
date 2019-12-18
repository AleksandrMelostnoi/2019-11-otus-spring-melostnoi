package ru.otus.homework02.service;


import ru.otus.homework02.model.Student;
import ru.otus.homework02.model.TestingResult;

import java.util.InputMismatchException;

public class UIServiceImpl implements UIService {

    private final LocalizationService localizationService;
    private final IOService ioService;

    public UIServiceImpl(LocalizationService localizationService, IOService ioService) {
        this.localizationService = localizationService;
        this.ioService = ioService;
    }

    @Override
    public int getUserAnswer(int maxAnswerNumber) {
        localizationService.outputTextForEnterCorrectAnswer();
        int answerNumber;
        try {
            answerNumber = ioService.inputNumber();
            if (1 > answerNumber || answerNumber > maxAnswerNumber) {
                localizationService.outputTextForRangeWarningMessage(maxAnswerNumber);
                return 0;
            }
        } catch (InputMismatchException e) {
            localizationService.outputTextForExceptionEerrorMessage(maxAnswerNumber);
            return 0;
        }
        return answerNumber;
    }

    @Override
    public Student readStudentInfo() {
        Student student = new Student();
        localizationService.outputTextForEnterFirstName();
        student.setFirstName(ioService.inputText());
        localizationService.outputTextForEnterLastName();
        student.setLastName(ioService.inputText());
        return student;
    }

    @Override
    public void printQuestion(int questionNumber, String questionText) {
        ioService.outputText("\n" + questionNumber + ". " + questionText);
    }

    @Override
    public void printResult(TestingResult testingResult) {
        localizationService.outputTextForResultMessage(testingResult.getFullName(), testingResult.getNumberOfCorrectAnswers(),
                testingResult.getQuestionsNumber());
    }

}
