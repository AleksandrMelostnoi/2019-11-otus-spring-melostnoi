package ru.otus.homework02.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework02.dao.QuizDao;
import ru.otus.homework02.dao.QuizDaoImpl;
import ru.otus.homework02.service.*;

@Configuration
public class AppConfig {

    @Bean
    QuizDao quizDao(@Value("${csv.path}") String path) {
        return new QuizDaoImpl(path);
    }

    @Bean
    IOService ioService() {
        return new IOServiceImpl();
    }

    @Bean
    UIService uiService(IOService ioService) {
        return new UIServiceImpl(ioService);
    }

    @Bean
    QuizService quizService(UIService uiService, QuizDao quizDao) {
        return new QuizServiceImpl(uiService, quizDao);
    }

}
