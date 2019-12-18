package ru.otus.homework03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import ru.otus.homework03.dao.QuizDao;
import ru.otus.homework03.exception.QuizDataFormatException;
import ru.otus.homework03.model.Question;

import java.util.List;

@DisplayName("Tests for quiz dao")
@SpringBootTest()
class QuizDaoTest {

    @Autowired
    private QuizDao dao;

    @Test
    void testGetQuestions() throws QuizDataFormatException {
        List<Question> questions = dao.readQuizzes();
        assertEquals(5, questions.size(), "Check questions number");
        questions.forEach(Assert::notNull);
    }

}