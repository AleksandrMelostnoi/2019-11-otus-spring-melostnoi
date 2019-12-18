package ru.otus.homework02.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.homework02.dao.QuizDao;
import ru.otus.homework02.dao.QuizDaoImpl;
import ru.otus.homework02.service.IOService;
import ru.otus.homework02.service.IOServiceImpl;
import ru.otus.homework02.service.LocalizationService;
import ru.otus.homework02.service.LocalizationServiceImpl;
import ru.otus.homework02.service.QuizService;
import ru.otus.homework02.service.QuizServiceImpl;
import ru.otus.homework02.service.UIService;
import ru.otus.homework02.service.UIServiceImpl;

import java.util.Locale;

@Configuration
public class AppConfig {

    @Bean
    QuizDao quizDao(@Value("${csv.path}") String path, @Value("${language}") String language) {
        return new QuizDaoImpl(String.format(path, language));
    }

    @Bean
    IOService ioService() {
        return new IOServiceImpl();
    }

    @Bean
    LocalizationService localizationService(@Value("${language}") String language, @Value("${country}") String country, IOService ioService) {
        ReloadableResourceBundleMessageSource messageSources = new ReloadableResourceBundleMessageSource();
        messageSources.setBasename("bundle");
        messageSources.setDefaultEncoding("UTF-8");
        return new LocalizationServiceImpl(Locale.forLanguageTag(language + "-" + country), ioService, messageSources);
    }

    @Bean
    UIService uiService(LocalizationService localizationService, IOService ioService) {
        return new UIServiceImpl(localizationService, ioService);
    }

    @Bean
    QuizService quizService(UIService uiService, QuizDao quizDao) {
        return new QuizServiceImpl(uiService, quizDao);
    }

}
