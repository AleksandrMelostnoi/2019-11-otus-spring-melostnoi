package ru.otus.homework01.util;

import ru.otus.homework01.model.Answer;
import ru.otus.homework01.model.Question;

public class Util {

    public static boolean checkAnswer(Question question, Integer userAnswer) {
        return question.getAnswerList().get(userAnswer - 1).getType();
    }

    public static String question2String(Question question) {
        StringBuilder output = new StringBuilder(question.getQuestionText() + "\n");
        for (Answer answer : question.getAnswerList()) {
            output.append(answer2StringFormat(answer));
        }
        return output.toString();
    }

    private static String answer2StringFormat(Answer answer) {
        return answer.getId() + ". " + answer.getAnswerText() + "\n";
    }

}
