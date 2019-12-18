package ru.otus.homework03.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@Data
public class LocalizationServiceImpl implements LocalizationService {

    private final IOService ioService;
    private Locale locale;
    private final MessageSource messageSources;

    @Autowired
    LocalizationServiceImpl(Locale locale, IOService ioService, MessageSource messageSources) {
        this.locale = locale;
        this.ioService = ioService;
        this.messageSources = messageSources;
    }

    public String outputTextForEnterCorrectAnswer() {
        return ioService.outputText(getMessage("enter.correct.answer") + " ");
    }

    public void outputTextForRangeWarningMessage(int maxAnswerNumber) {
        ioService.outputText(getMessage("range.warning.message") + " " + maxAnswerNumber + "\n");
    }

    public void outputTextForExceptionErrorMessage(int maxAnswerNumber) {
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
