package ru.otus.homework02;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.otus.homework02.dao.QuizDao;
import ru.otus.homework02.dao.QuizDaoImpl;
import ru.otus.homework02.exception.QuizDataFormatException;
import ru.otus.homework02.model.Question;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static ru.otus.homework02.util.Util.checkAnswer;
import static ru.otus.homework02.util.Util.question2String;

@DisplayName("Tests for utils")
class UtilTest {

    private static HashMap<String, String> suitableLanguageAnswer;

    @BeforeAll
    static void initAll() {
        suitableLanguageAnswer = new HashMap<>();
        suitableLanguageAnswer.put("ru", "Сколько дней в декабре ?\n1. 28\n2. 29\n3. 30\n4. 31\n");
        suitableLanguageAnswer.put("en", "How many days in december ?\n1. 28\n2. 29\n3. 30\n4. 31\n");
    }

    @ParameterizedTest
    @ValueSource(strings = {"en", "ru"})
    void testQuestion2String(String language) throws QuizDataFormatException {
        QuizDao dao = new QuizDaoImpl(String.format("questions_%s.csv", language));
        Question question = dao.readQuizzes("").get(0);
        assertEquals(suitableLanguageAnswer.get(language), question2String(question));
        assertTrue(checkAnswer(question, 4));
        assertFalse(checkAnswer(question, 1));
    }

}
