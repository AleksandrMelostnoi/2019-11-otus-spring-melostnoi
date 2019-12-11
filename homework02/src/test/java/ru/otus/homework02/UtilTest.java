package ru.otus.homework02;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework02.dao.QuizDao;
import ru.otus.homework02.exception.QuizDataFormatException;
import ru.otus.homework02.model.Question;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static ru.otus.homework02.util.Util.checkAnswer;
import static ru.otus.homework02.util.Util.question2String;

@DisplayName("Tests for utils")
class UtilTest {

    private static Question question;

    @BeforeAll
    static void initAll() throws QuizDataFormatException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context-test.xml");
        QuizDao dao = context.getBean(QuizDao.class);
        question = dao.readQuizzes().get(0);
    }

    @Test
    void testCheckAnswer() {
        assertTrue(checkAnswer(question, 4));
        assertFalse(checkAnswer(question, 1));
    }

    @Test
    void testQuestion2String() {
        assertEquals("How many days in december ?\n1. 28\n2. 29\n3. 30\n4. 31\n", question2String(question));
    }

}
