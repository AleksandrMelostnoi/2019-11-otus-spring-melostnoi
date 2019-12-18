package ru.otus.homework02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.homework02.exception.QuizDataFormatException;
import ru.otus.homework02.service.QuizService;

@PropertySource("app.properties")
@Configuration
@ComponentScan
public class App {

    public static void main(String[] args) throws QuizDataFormatException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        QuizService service = context.getBean(QuizService.class);
        service.startQuiz();
    }

}
