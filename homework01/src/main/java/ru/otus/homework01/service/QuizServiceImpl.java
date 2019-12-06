package ru.otus.homework01.service;

import ru.otus.homework01.dao.QuizDao;
import ru.otus.homework01.model.QuizRow;
import ru.otus.homework01.model.Student;
import java.util.List;

import static ru.otus.homework01.util.Util.checkAnswer;
import static ru.otus.homework01.util.Util.question2String;

public class QuizServiceImpl implements QuizService {

    private Student student;
    private InputConsoleService inputConsoleService;
    private QuizDao quizDao;

    QuizServiceImpl(InputConsoleService inputConsoleService, QuizDao quizDao) {
        this.quizDao = quizDao;
        this.inputConsoleService = inputConsoleService;
        student = new Student();
    }

    @Override
    public void startQuiz() {
        List<QuizRow> quizRows = quizDao.readQuizzes();
        readStudentName();
        int correctAnswers = 0;
        for (QuizRow quizRow : quizRows) {
            System.out.println("\n" + question2String(quizRow));
            Integer userAnswer = inputConsoleService.getUserAnswer(quizRow.getAnswerList().size());
            if (userAnswer <= 0) {
                continue;
            }
            if (checkAnswer(quizRow, userAnswer)) {
                correctAnswers++;
            }
        }
        System.out.println("Dear " + student.getFirstName() + " " + student.getLastName() + "!");
        System.out.println("Number of correct answers: " + correctAnswers + " out of " + quizRows.size() + "\n");
    }

    @Override
    public void readStudentName() {
        System.out.println("Please input first name");
        student.setFirstName(inputConsoleService.getName());
        System.out.println("Please input last name");
        student.setLastName(inputConsoleService.getName());
    }

}
