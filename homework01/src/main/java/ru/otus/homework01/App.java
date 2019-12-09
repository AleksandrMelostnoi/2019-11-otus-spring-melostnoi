package ru.otus.homework01;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework01.exception.QuizDataFormatException;
import ru.otus.homework01.service.QuizService;

public class App {

    public static void main(String[] args) throws QuizDataFormatException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuizService service = context.getBean(QuizService.class);
        service.startQuiz();
    }

}
