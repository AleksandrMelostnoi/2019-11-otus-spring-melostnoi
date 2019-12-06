package ru.otus.homework01.dao;

import ru.otus.homework01.App;
import ru.otus.homework01.model.Answer;
import ru.otus.homework01.model.Question;
import ru.otus.homework01.model.QuizRow;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuizDaoImpl implements QuizDao {

    private final String PATH;

    QuizDaoImpl(String PATH) {
        this.PATH = PATH;
    }

    @Override
    public List<QuizRow> readQuizzes() {
        List<String> rows = null;
        try {
            rows = Files.readAllLines(Paths.get(Objects.requireNonNull(App.class.getClassLoader().getResource(PATH)).toURI()));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return parseQARows(rows);
    }

    private List<QuizRow> parseQARows(List<String> rows) {
        List<QuizRow> quizRows = new ArrayList<>();
        if (null == rows) {
            return quizRows;
        }
        for (String row : rows) {
            String[] columns = row.split(";");
            QuizRow quizRow = new QuizRow();
            quizRow.setQuestion(new Question(columns[0]));
            List<Answer> answerList = new ArrayList<>();
            for (int i = 1; i < columns.length; i++) {
                answerList.add(new Answer((i + 1)/2,columns[i], Boolean.valueOf(columns[i + 1])));
                i++;
            }
            quizRow.setAnswerList(answerList);
            quizRows.add(quizRow);
        }
        return quizRows;
    }

}
