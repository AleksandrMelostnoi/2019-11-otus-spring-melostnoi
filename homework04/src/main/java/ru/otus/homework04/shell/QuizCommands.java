package ru.otus.homework04.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.homework04.exception.QuizDataFormatException;
import ru.otus.homework04.service.QuizService;

import static org.springframework.shell.Availability.available;
import static org.springframework.shell.Availability.unavailable;

@ShellComponent
@RequiredArgsConstructor
public class QuizCommands {

    private final QuizService quizService;

    @ShellMethod(value = "login", key = {"login"})
    public void login() {
        quizService.login();
    }

    @ShellMethodAvailability("isLogin")
    @ShellMethod(value = "start quiz", key = {"start-quiz"})
    public void startQuiz() throws QuizDataFormatException {
        quizService.startQuiz();
    }

    private Availability isLogin() {
        return quizService.isLogin() ? available() : unavailable("need login.");
    }

}
