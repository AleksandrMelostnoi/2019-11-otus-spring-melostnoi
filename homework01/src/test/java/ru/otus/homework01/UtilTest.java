package ru.otus.homework01;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework01.dao.QuizDao;
import ru.otus.homework01.model.QuizRow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static ru.otus.homework01.util.Util.checkAnswer;
import static ru.otus.homework01.util.Util.question2String;

@DisplayName("Tests for utils")
class UtilTest {

    private static QuizRow quizRow;

    @BeforeAll
    static void initAll() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context-test.xml");
        QuizDao dao = context.getBean(QuizDao.class);
        quizRow = dao.readQuizzes().get(0);
    }

    @Test
    void testCheckAnswer() {
        assertTrue(checkAnswer(quizRow, 4));
        assertFalse(checkAnswer(quizRow, 1));
    }

    @Test
    void testQuestion2String() {
        assertEquals("How many days in december ?\n1. 28\n2. 29\n3. 30\n4. 31\n", question2String(quizRow));
    }

}
