package ru.otus.homework02.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.homework02.dao.QuizDao;
import ru.otus.homework02.dao.QuizDaoImpl;
import ru.otus.homework02.service.*;

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
    UIService uiService(@Value("${language}") String language, IOService ioService) {
        ReloadableResourceBundleMessageSource messageSources = new ReloadableResourceBundleMessageSource();
        messageSources.setBasename("bundle");
        messageSources.setDefaultEncoding("CP1251");
        return new UIServiceImpl(Locale.forLanguageTag(language), ioService, messageSources);
    }

    @Bean
    QuizService quizService(UIService uiService, QuizDao quizDao) {
        return new QuizServiceImpl(uiService, quizDao);
    }

}
