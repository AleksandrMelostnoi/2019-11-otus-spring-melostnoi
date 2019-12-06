package ru.otus.homework01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;
import ru.otus.homework01.dao.QuizDao;
import ru.otus.homework01.model.QuizRow;

import java.util.List;

@DisplayName("Tests for quiz dao")
class QuizDaoTest {

    private static QuizDao dao;

    @BeforeAll
    static void initAll() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context-test.xml");
        dao = context.getBean(QuizDao.class);
    }

    @Test
    void testGetQuestions() {
        List<QuizRow> questions = dao.readQuizzes();
        assertEquals(5, questions.size(), "Check questions number");
        questions.forEach(Assert::notNull);
    }

}