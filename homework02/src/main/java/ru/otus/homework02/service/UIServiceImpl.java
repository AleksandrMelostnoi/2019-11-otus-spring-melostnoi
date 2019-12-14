package ru.otus.homework02.service;


import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.homework02.model.Student;
import ru.otus.homework02.model.TestingResult;

import java.util.InputMismatchException;
import java.util.Locale;

public class UIServiceImpl implements UIService {

    private final IOService ioService;
    private final Locale locale;
    private final ReloadableResourceBundleMessageSource messageSources;

    public UIServiceImpl(Locale locale, IOService ioService, ReloadableResourceBundleMessageSource messageSources) {
        this.ioService = ioService;
        this.locale = locale;
        this.messageSources = messageSources;
    }

    @Override
    public int getUserAnswer(int maxAnswerNumber) {
        ioService.outputText(getMessage("enter.correct.answer") + " ");
        int answerNumber;
        try {
            answerNumber = ioService.inputNumber();
            if (1 > answerNumber || answerNumber > maxAnswerNumber) {
                ioService.outputText(getMessage("range.warning.message") + " " + maxAnswerNumber + "\n");
                return 0;
            }
        } catch (InputMismatchException e) {
            ioService.outputText(getMessage("exception.error.message") + " " + maxAnswerNumber + "\n");
            return 0;
        }
        return answerNumber;
    }

    @Override
    public Student readStudentInfo() {
        Student student = new Student();
        ioService.outputText(getMessage("enter.first.name"));
        student.setFirstName(ioService.inputText());
        ioService.outputText(getMessage("enter.last.name"));
        student.setLastName(ioService.inputText());
        return student;
    }

    @Override
    public void printQuestion(int questionNumber, String questionText) {
        ioService.outputText("\n" + questionNumber + ". " + questionText);
    }

    @Override
    public void printResult(TestingResult testingResult) {
        String firstRow = testingResult.getFullName() + "!" + "\n";
        String secondRow = getMessage("correct.answers.number") + " " +
                testingResult.getNumberOfCorrectAnswers() + " " +
                getMessage("out.of") + " " + testingResult.getQuestionsNumber() + "\n";
        ioService.outputText(firstRow + secondRow);
    }

    public String getMessage(String source) {
        return messageSources.getMessage(source, null, locale);
    }

}
