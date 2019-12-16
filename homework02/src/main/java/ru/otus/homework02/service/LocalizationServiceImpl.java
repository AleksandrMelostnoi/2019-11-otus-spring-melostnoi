package ru.otus.homework02.service;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

public class LocalizationServiceImpl implements LocalizationService {

    private final IOService ioService;
    private final Locale locale;
    private final MessageSource messageSources;

    public LocalizationServiceImpl(Locale locale, IOService ioService, ReloadableResourceBundleMessageSource messageSources) {
        this.ioService = ioService;
        this.locale = locale;
        this.messageSources = messageSources;
    }

    public void outputTextForEnterCorrectAnswer() {
        ioService.outputText(getMessage("enter.correct.answer") + " ");
    }

    public void outputTextForRangeWarningMessage(int maxAnswerNumber) {
        ioService.outputText(getMessage("range.warning.message") + " " + maxAnswerNumber + "\n");
    }

    public void outputTextForExceptionEerrorMessage(int maxAnswerNumber) {
        ioService.outputText(getMessage("exception.error.message") + " " + maxAnswerNumber + "\n");
    }

    public void outputTextForEnterFirstName() {
        ioService.outputText(getMessage("enter.first.name"));
    }

    public void outputTextForEnterLastName() {
        ioService.outputText(getMessage("enter.last.name"));
    }

    public void outputTextForResultMessage(String studentFullMessage, int correctAnswers, int questionsNumber) {
        String resultMessage = getMessage("correct.answers.number") + " " + correctAnswers + " " +
                getMessage("out.of") + " " + questionsNumber + "\n";
        ioService.outputText(studentFullMessage + "!" + "\n" + resultMessage);
    }

    private String getMessage(String source) {
        return messageSources.getMessage(source, null, locale);
    }
}
