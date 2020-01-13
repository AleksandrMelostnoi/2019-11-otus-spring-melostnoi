package ru.otus.homework03.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class LocalizationServiceImpl implements LocalizationService {

    private final IOService ioService;
    private MessageSourceWrapper messageSourceWrapper;

    @Autowired
    LocalizationServiceImpl(IOService ioService, MessageSourceWrapper messageSourceWrapper) {
        this.ioService = ioService;
        this.messageSourceWrapper = messageSourceWrapper;
    }

    public String outputLocalizedMsgForEnterCorrectAnswer() {
        return ioService.outputText(messageSourceWrapper.getMessage("enter.correct.answer") + " ");
    }

    public void outputLocalizedMsgForRangeWarningMessage(int maxAnswerNumber) {
        ioService.outputText(messageSourceWrapper.getMessage("range.warning.message") + " " + maxAnswerNumber);
    }

    public void outputLocalizedMsgForExceptionErrorMessage(int maxAnswerNumber) {
        ioService.outputText(messageSourceWrapper.getMessage("exception.error.message") + " " + maxAnswerNumber);
    }

    public void outputLocalizedMsgForEnterFirstName() {
        ioService.outputText(messageSourceWrapper.getMessage("enter.first.name"));
    }

    public void outputLocalizedMsgForEnterLastName() {
        ioService.outputText(messageSourceWrapper.getMessage("enter.last.name"));
    }

    public void outputLocalizedMsgForResultMessage(String studentFullMessage, int correctAnswers, int questionsNumber) {
        String resultMessage = messageSourceWrapper.getMessage("correct.answers.number") + " " + correctAnswers + " " +
                messageSourceWrapper.getMessage("out.of") + " " + questionsNumber + "\n";
        ioService.outputText(studentFullMessage + "!" + "\n" + resultMessage);
    }

}
