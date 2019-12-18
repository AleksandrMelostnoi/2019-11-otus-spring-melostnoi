package ru.otus.homework02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;
import ru.otus.homework02.dao.QuizDao;
import ru.otus.homework02.exception.QuizDataFormatException;
import ru.otus.homework02.model.Question;

import java.util.List;

@DisplayName("Tests for quiz dao")
class QuizDaoTest {

    private static QuizDao dao;

    @BeforeAll
    static void initAll() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        dao = context.getBean(QuizDao.class);
    }

    @Test
    void testGetQuestions() throws QuizDataFormatException {
        List<Question> questions = dao.readQuizzes();
        assertEquals(5, questions.size(), "Check questions number");
        questions.forEach(Assert::notNull);
    }

}