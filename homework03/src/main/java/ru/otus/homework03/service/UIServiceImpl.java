package ru.otus.homework03.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework03.model.Student;
import ru.otus.homework03.model.TestingResult;

import java.util.InputMismatchException;

@Service
public class UIServiceImpl implements UIService {

    private final LocalizationService localizationService;
    private final IOService ioService;

    @Autowired
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
            localizationService.outputTextForExceptionErrorMessage(maxAnswerNumber);
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
