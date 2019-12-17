package ru.otus.homework03.service;

import org.springframework.stereotype.Service;
import ru.otus.homework03.dao.QuizDao;
import ru.otus.homework03.exception.QuizDataFormatException;
import ru.otus.homework03.model.Question;
import ru.otus.homework03.model.Student;
import ru.otus.homework03.model.TestingResult;

import java.util.List;

import static ru.otus.homework03.util.QuestionsUtils.checkAnswer;
import static ru.otus.homework03.util.QuestionsUtils.question2String;

@Service
public class QuizServiceImpl implements QuizService {

    private final UIService uiService;
    private final QuizDao quizDao;

    public QuizServiceImpl(UIService uiService, QuizDao quizDao) {
        this.quizDao = quizDao;
        this.uiService = uiService;
    }

    @Override
    public void startQuiz() throws QuizDataFormatException {
        Student student = uiService.readStudentInfo();
        List<Question> questions = quizDao.readQuizzes();
        int correctAnswers = 0;
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            uiService.printQuestion(i + 1, question2String(question));
            int userAnswer = uiService.getUserAnswer(question.getAnswerList().size());
            if (userAnswer <= 0) {
                continue;
            }
            if (checkAnswer(question, userAnswer)) {
                correctAnswers++;
            }
        }
        uiService.printResult(new TestingResult(student, correctAnswers, questions.size()));
    }


}
