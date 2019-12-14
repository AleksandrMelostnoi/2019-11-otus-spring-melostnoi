package ru.otus.homework02.dao;

import ru.otus.homework02.App;
import ru.otus.homework02.exception.QuizDataFormatException;
import ru.otus.homework02.model.Answer;
import ru.otus.homework02.model.Question;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuizDaoImpl implements QuizDao {

    private final String path;

    public QuizDaoImpl(String path) {
        this.path = path;
    }

    @Override
    public List<Question> readQuizzes(String exceptionMessage) throws QuizDataFormatException {
        List<String> rows = null;
        try {
            rows = Files.readAllLines(Paths.get(Objects.requireNonNull(App.class.getClassLoader().getResource(path)).toURI()));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return parseQARows(rows, exceptionMessage);
    }

    private List<Question> parseQARows(List<String> rows, String exceptionMessage) throws QuizDataFormatException {
        List<Question> quizRows = new ArrayList<>();
        if (null == rows) {
            return quizRows;
        }
        for (String row : rows) {
            String[] columns = row.split(";");
            if (columns.length <= 1) {
                throw new QuizDataFormatException(exceptionMessage);
            }
            Question question = new Question();
            question.setQuestionText(columns[0]);
            List<Answer> answerList = new ArrayList<>();
            for (int i = 1; i < columns.length; i++) {
                answerList.add(new Answer((i + 1) / 2, columns[i], Boolean.parseBoolean(columns[i + 1])));
                i++;
            }
            question.setAnswerList(answerList);
            quizRows.add(question);
        }
        return quizRows;
    }

}
