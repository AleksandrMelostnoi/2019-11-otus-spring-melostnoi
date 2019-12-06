package ru.otus.homework01.util;

import ru.otus.homework01.model.Answer;
import ru.otus.homework01.model.QuizRow;

public class Util {

    public static boolean checkAnswer(QuizRow quizRow, Integer userAnswer) {
        return quizRow.getAnswerList().get(userAnswer - 1).getType();
    }

    public static String question2String(QuizRow quizRow) {
        StringBuilder output = new StringBuilder(quizRow.getQuestion().getQuestionText() + "\n");
        for (Answer answer : quizRow.getAnswerList()) {
            output.append(answer2StringFormat(answer));
        }
        return output.toString();
    }

    private static String answer2StringFormat(Answer answer) {
        return answer.getId() + ". " + answer.getAnswerText() + "\n";
    }

}
