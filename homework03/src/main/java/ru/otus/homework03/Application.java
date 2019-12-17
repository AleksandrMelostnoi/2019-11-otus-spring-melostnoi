package ru.otus.homework03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.homework03.exception.QuizDataFormatException;
import ru.otus.homework03.service.QuizService;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws QuizDataFormatException {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        QuizService service = context.getBean(QuizService.class);
        service.startQuiz();
    }

}
