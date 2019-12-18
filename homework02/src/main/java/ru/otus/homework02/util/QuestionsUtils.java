package ru.otus.homework02.util;

import ru.otus.homework02.model.Answer;
import ru.otus.homework02.model.Question;

public class QuestionsUtils {

    public static boolean checkAnswer(Question question, Integer userAnswer) {
        return question.getAnswerList().get(userAnswer - 1).isType();
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
