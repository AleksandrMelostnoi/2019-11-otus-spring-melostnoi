package ru.otus.homework03;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework03.service.LocalizationServiceImpl;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LocalizationServiceTest {

    @Autowired
    private LocalizationServiceImpl localizationService;

    @Test
    void englishLocalizationServiceTest() {
        localizationService.setLocale(Locale.forLanguageTag("en-EN"));
        assertEquals("Enter number of correct answer: ", localizationService.outputTextForEnterCorrectAnswer());
    }

    @Test
    void russianLocalizationServiceTest() {
        localizationService.setLocale(Locale.forLanguageTag("ru-RU"));
        assertEquals("Введите номер правильного ответа: ", localizationService.outputTextForEnterCorrectAnswer());
    }

}
