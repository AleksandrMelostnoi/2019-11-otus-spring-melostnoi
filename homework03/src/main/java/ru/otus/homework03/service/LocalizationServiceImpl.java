package ru.otus.homework03.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class LocalizationServiceImpl implements LocalizationService {

    private final IOService ioService;
    private MessageWrapper messageWrapper;

    @Autowired
    LocalizationServiceImpl(IOService ioService, MessageWrapper messageWrapper) {
        this.ioService = ioService;
        this.messageWrapper = messageWrapper;
    }

    public String outputLocalizedMsgForEnterCorrectAnswer() {
        return ioService.outputText(messageWrapper.getMessage("enter.correct.answer") + " ");
    }

    public void outputLocalizedMsgForRangeWarningMessage(int maxAnswerNumber) {
        ioService.outputText(messageWrapper.getMessage("range.warning.message") + " " + maxAnswerNumber + "\n");
    }

    public void outputLocalizedMsgForExceptionErrorMessage(int maxAnswerNumber) {
        ioService.outputText(messageWrapper.getMessage("exception.error.message") + " " + maxAnswerNumber + "\n");
    }

    public void outputLocalizedMsgForEnterFirstName() {
        ioService.outputText(messageWrapper.getMessage("enter.first.name"));
    }

    public void outputLocalizedMsgForEnterLastName() {
        ioService.outputText(messageWrapper.getMessage("enter.last.name"));
    }

    public void outputLocalizedMsgForResultMessage(String studentFullMessage, int correctAnswers, int questionsNumber) {
        String resultMessage = messageWrapper.getMessage("correct.answers.number") + " " + correctAnswers + " " +
                messageWrapper.getMessage("out.of") + " " + questionsNumber + "\n";
        ioService.outputText(studentFullMessage + "!" + "\n" + resultMessage);
    }

}
